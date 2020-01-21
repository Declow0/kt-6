import ru.netology.hiearchy.view.TextView
import ru.netology.hiearchy.view.View
import ru.netology.hiearchy.view.ViewGroup
import ru.netology.hiearchy.widget.Button

fun main() {
    val view = View()
    view.click()

    println()

    val text = TextView("Some Text")
    text.click()
    println(text.text)
    text.text = "Something bad happened"
    println(text.text)

    println()

    val button = Button("Click me")
    button.click()
    println(button.text)
    button.text = "Don't click me"
    println(button.text)

    println()

    val main = ViewGroup()
    val title = TextView("Main Screen")
    main.addView(title)
    main.view.click()
    println((main.view as TextView).text)

    println()

    val content = ViewGroup()
    val readMore = Button("Read more")
    content.addView(readMore)
    content.view.click()
    println((content.view as Button).text)
}