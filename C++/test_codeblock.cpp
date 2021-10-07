#include <iostream>
using namespace std;
/*int main() {
    int time = 10;
    string result = (time < 18) ? "Good day." : "Good evening.";
    cout << result << endl;
    string food = "Pizza";  // A food variable of type string
    string* ptr = &food;    // A pointer variable, with the name ptr, that stores the address of food

// Output the value of food (Pizza)
    cout << food << "\n";

// Output the memory address of food (0x6dfed4)
    cout << &food << "\n";

// Output the memory address of food with the pointer (0x6dfed4)
    cout << *ptr << "\n";
    return 0;
}*/

class Car {        // The class
  public:          // Access specifier
    string brand;  // Attribute
    string model;  // Attribute
    int year;      // Attribute
    Car(string x, string y, int z) { // Constructor with parameters
      brand = x;
      model = y;
      year = z;
    }
};

int main() {
  // Create Car objects and call the constructor with different values
  Car carObj1("BMW", "X5", 1999);
  Car carObj2("Ford", "Mustang", 1969);

  // Print values
  cout << carObj1.brand << " " << carObj1.model << " " << carObj1.year << "\n";
  cout << carObj2.brand << " " << carObj2.model << " " << carObj2.year << "\n";
  return 0;
}
