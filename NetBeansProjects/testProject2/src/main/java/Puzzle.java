// Contains complete puzzle specification

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import fileMenu.Parser;

import lightsOutGraph.gui.LightsOutGraph;

public class Puzzle implements ICallBack {

    Graph graph;
    Matrix matrix = new Matrix(1, 2);
    boolean initialised = false;
    private boolean saved = true;
    private String description = "";
    private String notes = "";

    public Puzzle() {
        graph = new Graph(this);
    }
    ActionListener listener;

//	all get/set stuff
    public Graph getGraph() {
        return graph;
    }

    public void clearAll() {
        matrix = null;
        graph.clear();
        initialised = false;
    }

    public void setActionListener(ActionListener al) {
        listener = al;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        String newtext = d == null ? "" : d;
        if (!newtext.equals(description)) {
            description = newtext;
            setChanged();
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String d) {
        String newtext = d == null ? "" : d;
        if (!newtext.equals(notes)) {
            notes = newtext;
            setChanged();
        }
    }

// initialise just before solving / playing
    public void initialise() {
        if (initialised) {
            return;
        }
        int num = graph.getNumNodes();
        int numStates = graph.getNumStates();
        matrix.reset(num, numStates);
        graph.resetState();

        // fill matrix
        Iterable<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            int n1 = graph.getNodeIndex(edge.getNode1());
            int n2 = graph.getNodeIndex(edge.getNode2());
            if (n1 == n2) {
                int v = (edge.valence1 - edge.valence2 + numStates) % numStates;
                matrix.set(n1, n2, v);
            } else {
                matrix.set(n1, n2, edge.valence1);
                matrix.set(n2, n1, edge.valence2);
            }
        }
        initialised = true;
    }

    // get quiet pattern
    public void optimise(int[] solution) {
        int[][] quiet = matrix.getQuiet();

        if (quiet.length == 0) {
            return;
        }
        int n = solution.length;
        int[] current = new int[n];
        int[] best = new int[n];
        int bestCount = -1;
        int numpat = 1 << quiet.length;
        for (int pat = 0; pat < numpat; pat++) {
            // construct the pattern
            for (int i = 0; i < n; i++) {
                current[i] = solution[i];	// original solution
            }
            constructQuiet(current, pat);
            // count
            int count = 0;
            for (int i = 0; i < n; i++) {
                count += current[i];
            }
            // see if best found so far
            if (bestCount < 0 || bestCount > count) {
                bestCount = count;
                for (int i = 0; i < n; i++) {
                    best[i] = current[i];   // update best
                }
            }
        }
        // return best solution
        for (int i = 0; i < n; i++) {
            solution[i] = best[i];
        }
    }

    private void constructQuiet(int[] pattern, int patnr) {
        int[][] quiet = matrix.getQuiet();
        int ns = graph.getNumStates();

        int k = patnr;
        // construct the pattern
        for (int j = 0; j < quiet.length; j++) {
            int c = k % ns;
            k /= ns;
            if (c != 0) {
                for (int i = 0; i < pattern.length; i++) {
                    pattern[i] += c * quiet[j][i];	// add quiet pattern
                }
            }
        }
        for (int i = 0; i < pattern.length; i++) {
            pattern[i] %= ns;	// reduce
        }
    }

    public int getNumQuiet() {
        if (matrix.getQuiet() == null) {
            return 0;
        }
        int ns = graph.getNumStates();
        int nlty = getNullity();
        double nq = Math.pow(ns, nlty);
        return nq > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) nq;
    }

    public int getNullity() {
        if (matrix.getQuiet() == null) {
            return 0;
        }
        return matrix.getQuiet().length;
    }

    public void setQuiet(int patnr) {
        int[] pattern = new int[graph.getNumNodes()];
        constructQuiet(pattern, patnr);
        graph.clearState();
        graph.setSolution(pattern);
    }

    static public final int STARTANALYSIS = 0;
    static public final int INITIALISEDANALYSIS = 1;
    static public final int ENDANALYSIS = 2;
    static public final int STARTSOLVE = 3;
    static public final int INITIALISEDSOLVE = 4;
    static public final int ENDINVERSION = 5;
    static public final int UNSOLVABLE = 6;
    static public final int STARTOPTIMISE = 7;
    static public final int ENDOPTIMISE = 8;
    static public final int SOLVABLE = 9;

    void fireEvent(int id) {
        if (listener != null) {
            listener.actionPerformed(new ActionEvent(this, id, ""));
        }
    }
    Thread thread;

    public void startAnalysing() {
        if (thread != null) {
            return;
        }

        Runnable task = new Runnable() {
            public void run() {
                fireEvent(STARTANALYSIS);
                if (!initialised) {
                    initialise();
                    fireEvent(INITIALISEDANALYSIS);
                }
                matrix.calculateInverse();
                fireEvent(ENDANALYSIS);
                thread = null;
            }
        };
        thread = new Thread(task);
        thread.start();
    }

    public void startSolving(final boolean optimise) {
        if (thread != null) {
            return;
        }

        Runnable task = new Runnable() {
            public void run() {
                fireEvent(STARTSOLVE);
                if (!initialised) {
                    initialise();
                    fireEvent(INITIALISEDSOLVE);
                }
                matrix.calculateInverse();
                fireEvent(ENDINVERSION);
                int state[] = graph.getState();
                int[] solution = matrix.getSolution(state);
                if (solution == null) {
                    fireEvent(UNSOLVABLE);
                } else {
                    if (optimise) {
                        fireEvent(STARTOPTIMISE);
                        optimise(solution);
                        fireEvent(ENDOPTIMISE);
                    } else {
                        fireEvent(SOLVABLE);
                    }
                    graph.setSolution(solution);
                }

                thread = null;
            }
        };
        thread = new Thread(task);
        thread.start();
    }

    public boolean isRunning() {
        return thread != null;
    }

//	all text io stuff
    public static Puzzle parse(Reader r) throws IOException {
        BufferedReader br = new BufferedReader(r);
        Puzzle p = new Puzzle();
        String d = br.readLine();
        p.description = d.replace('\n', '\t');
        Parser pr = new Parser(br);
        p.graph = Graph.parse(pr, p);
        String s = pr.readString(true);
        if ("Notes".equals(s)) {
            p.notes = Parser.decode(pr.readString());
        } else {
            pr.pushback();
        }
        p.setNoUnsavedChanges();
        return p;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(description.replace('\n', '\t'));
        sb.append('\n');
        sb.append(graph.toString());
        sb.append('\n');
        if (!notes.isEmpty()) {
            sb.append("Notes ");
            sb.append(Parser.encode(notes));
            sb.append('\n');
        }
        return sb.toString();
    }

    public void callback() {
        // graph has changed
        setChanged();
    }

    private void setChanged() {
        initialised = false;
        saved = false;
        LightsOutGraph.updateTitle();
    }

    public boolean hasUnsavedChanges() {
        return !saved;
    }

    public void setNoUnsavedChanges() {
        saved = true;
        LightsOutGraph.updateTitle();
    }

    // clear all savable content. Called when the New menu option chosen.
    public void clear() {
        graph.clear();
        setDescription(null);
        setNoUnsavedChanges();
    }
}
