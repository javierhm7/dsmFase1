import kotlin.system.exitProcess

// TIP: To run this code, press the Run shortcut (or click the execute icon in the gutter).
// This application simulates a simple shopping cart system where you can add, remove, view, and checkout products.
fun main() {

    // Create a mutable list to store the available products.
    // Each product is expected to be an instance of the Producto class containing its name, price, and available quantity.
    val lista = mutableListOf<Producto>()
    
    // Create a mutable list to represent the shopping cart.
    // Each item in the cart is a Pair consisting of a Producto and an integer representing the quantity added.
    val carrito = mutableListOf<Pair<Producto, Int>>()
    
    // Define console text color variables using ANSI escape codes.
    // These colors will be used to highlight messages in the console (e.g., error messages or menu options).
    // Note: The 'control' variable is used to maintain the loop but its value remains unchanged.
    val control = 1
    val ANSI_RESET = "\u001B[0m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_GREEN = "\u001B[32m"

    // Adding products to the available products list.
    // Each product is added with a name, a price, and a quantity.
    // The product names have been updated to new items.
    lista.add(Producto("Smartphone", 150.5, 5))
    lista.add(Producto("Wireless Earbuds", 125.99, 4))
    lista.add(Producto("Smart TV", 350.50, 8))

    // Main loop to handle user interactions.
    // The loop continues indefinitely until the user selects the exit option.
    while (control != 0) {
        // Display the list of available products on the screen.
        Utils().mostrarproductos(lista)
        
        // Display the main menu with available actions.
        // The menu is displayed with blue text for a clear visual separation.
        Utils().menuPrincipal(ANSI_BLUE, ANSI_RESET)
        
        // Read the user's input from the console and attempt to convert it to an integer.
        var opcion: Int? = readlnOrNull()?.toIntOrNull()
        
        // Validate that the entered option is within the valid range (0 to 4).
        // If the user enters an invalid option, display an error message in red and prompt again.
        while (opcion !in 0..4) {
            println(ANSI_RED + "Error: Invalid option. Please try again." + ANSI_RESET)
            opcion = readlnOrNull()?.toIntOrNull()
        }
        
        // Execute a different action based on the user's selection.
        when(opcion) {
            // Option 1: Add a product to the shopping cart.
            1 -> {
                CarritoItems().agregarProducto(carrito, lista, ANSI_RESET, ANSI_RED)
                Utils().mensajePausa()  // Pause to allow the user to review the action before returning to the menu.
            }
            // Option 2: Remove a product from the shopping cart.
            2 -> {
                CarritoItems().eliminarProducto(carrito, lista, ANSI_RESET, ANSI_RED)
                Utils().mensajePausa()  // Pause to allow the user to review the action before returning to the menu.
            }
            // Option 3: View the current contents of the shopping cart.
            3 -> {
                CarritoItems().verCarrito(carrito, lista, ANSI_RESET, ANSI_RED)
                Utils().mensajePausa()  // Pause to allow the user to review the cart contents before returning to the menu.
            }
            // Option 4: Proceed to checkout by generating a bill for the current shopping cart contents.
            4 -> {
                CarritoItems().facturar(carrito, lista, ANSI_RESET, ANSI_RED, ANSI_GREEN, ANSI_BLUE)
                Utils().mensajePausa()  // Pause to allow the user to review the billing information.
            }
            // Option 0: Exit the application.
            0 -> {
                exitProcess(0)
            }
        }
    }
}
