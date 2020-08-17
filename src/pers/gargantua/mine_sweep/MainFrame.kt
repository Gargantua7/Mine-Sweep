package pers.gargantua.mine_sweep

import java.awt.BorderLayout
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel


/**
 * @author Gargantua丶
 **/

object MainFrame : JFrame() {

    @JvmStatic
    fun main(args: Array<String>) {
        MainFrame
    }

    val mainPanel: JPanel = JPanel()
    private val gamePanel: JPanel = JPanel()
    val cardLayout = CardLayout()

    init {
        gamePanel.apply {
            layout = BorderLayout()
            add(Gaming, BorderLayout.CENTER)
            add(StaBar, BorderLayout.SOUTH)
        }

        mainPanel.apply {
            layout = cardLayout
            add("start", Start)
            add("gaming", gamePanel)
        }

        layout = BorderLayout()

        add(Menu, BorderLayout.NORTH)
        add(mainPanel, BorderLayout.CENTER)

        title = "Mine Sweep"
        setSize(300, 230)
        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        cardLayout.show(mainPanel, "start")
    }
}