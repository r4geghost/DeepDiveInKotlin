package moduleThreeOOP.corporation

enum class OperationCodes(val title: String) {
    EXIT(title = "Exit"),
    REGISTER_NEW_ITEM(title = "Register new item"),
    SHOW_ALL_ITEMS(title = "Show all items"),
    REMOVE_PRODUCT_CARD(title = "Remove product card"),
    REGISTER_NEW_EMPLOYEE(title = "Register new employee"),
    FIRE_AN_EMPLOYEE(title = "Fire an employee"),
    SHOW_ALL_EMPLOYEES(title = "Show all employees"),
    CHANGE_SALARY(title = "Change salary"),
}
