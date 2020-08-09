package pers.gargantua.mine_sweep

import java.awt.*
import javax.swing.*

/**
 * @author Gargantua丶
 **/

class MyLabel(text: String, gridBagLayout: GridBagLayout, gridBagConstraints: GridBagConstraints) : JLabel() {
    init {
        this.text = text
        font = MyStyle.FONT
        gridBagLayout.setConstraints(this, gridBagConstraints)
    }
}

class MyText(text: String, gridBagLayout: GridBagLayout, gridBagConstraints: GridBagConstraints) : JTextField() {
    init {
        this.text = text
        font = MyStyle.FONT
        gridBagLayout.setConstraints(this, gridBagConstraints)
    }
}

class MyButton(text: String, gridBagLayout: GridBagLayout, gridBagConstraints: GridBagConstraints) : JButton() {
    init {
        this.text = text
        font = MyStyle.FONT
        background = MyStyle.GROUND
        gridBagLayout.setConstraints(this, gridBagConstraints)
    }
}

class MyDialog(title: String = "Topic", context: String, width: Int = 200, height: Int = 150, sta: Boolean = true) :
    JDialog() {

    init {
        val str = if (sta) {
            StaBar.stop()
            "New Game"
        } else "OK"
        val label = JLabel(context, JLabel.CENTER).apply { font = MyStyle.FONT }
        val scroll = JScrollPane(label).apply { verticalScrollBar.unitIncrement = 20 }
        val button = JButton(str).apply {
            font = MyStyle.FONT
            background = Color.WHITE
            addActionListener {
                this@MyDialog.dispose()
                if(sta) {
                    MainFrame.cardLayout.show(MainFrame.mainPanel, "start")
                    MainFrame.setSize(300, 230)
                }
            }
        }
        layout = BorderLayout()
        add(scroll, BorderLayout.CENTER)
        add(button, BorderLayout.SOUTH)
        this.title = title
        isModal = true
        isResizable = true
        setSize(width, height)
        setLocationRelativeTo(null)
        isVisible = true
    }
}

object MyStyle {
    val GROUND: Color = Color.WHITE
    val B: Color = Color(221, 221, 221)
    val F: Color = Color.ORANGE
    val M: Color = Color.RED
    val ZERO: Color = Color(110, 110, 110)
    val CHOOSE: Color = Color(194, 194, 194)
    val FONT = Font("SansSerif", Font.PLAIN, 16)
    val FONT_B = Font("SansSerif", Font.BOLD, 16)
    val FONT_S = Font("SansSerif", Font.PLAIN, 14)
    val NUMBER = listOf<Color>(
        Color(110, 110, 110),
        Color.BLUE,
        Color(0, 136, 0),
        Color.RED,
        Color(0, 34, 102),
        Color(102, 0, 0),
        Color(85, 0, 0),
        Color.BLACK,
        Color.GRAY
    )
}