package pers.gargantua.mine_sweep

import java.awt.BorderLayout
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel


/**
 * @author Gargantua丶
 **/
fun main() {
    MainFrame.showFrame()
}

object MainFrame : JFrame() {

    val mainPanel: JPanel = JPanel()
    private val gamePanel: JPanel = JPanel()
    val cardLayout = CardLayout()

    fun showFrame() {
        gamePanel.apply {
            layout = BorderLayout()
            add(Gaming.init(), BorderLayout.CENTER)
            add(StaBar.initialize(), BorderLayout.SOUTH)
        }

        mainPanel.apply {
            layout = cardLayout
            add("start", Start.init())
            add("gaming", gamePanel)
        }

        layout = BorderLayout()

        add(Menu.init(), BorderLayout.NORTH)
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