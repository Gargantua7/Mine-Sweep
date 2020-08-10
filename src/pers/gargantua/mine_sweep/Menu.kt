package pers.gargantua.mine_sweep

import java.awt.Color
import java.io.File
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import kotlin.system.exitProcess

/**
 * @author Gargantua丶
 **/
object Menu : JMenuBar() {

    private var isFirstInit = true

    fun init(): Menu {
        return if (isFirstInit) {
            isFirstInit = false
            initialize()
        } else this
    }

    private fun initialize(): Menu {
        add(FileMenu.initialize())
        add(AboutMenu.initialize())
        return this
    }
}

object FileMenu : JMenu("File") {

    private var isFirstInit = true

    fun init(): FileMenu {
        return if (isFirstInit) {
            isFirstInit = false
            initialize()
        } else this
    }

    fun initialize(): FileMenu {
        font = MyStyle.FONT_S
        add(JMenuItem("New Game").apply {
            font = MyStyle.FONT_S
            addActionListener {
                StaBar.stop()
                MainFrame.cardLayout.show(MainFrame.mainPanel, "start")
                MainFrame.setSize(300, 200)
            }
        })
        addSeparator()
        add(JMenuItem("Exit").apply {
            font = MyStyle.FONT_S
            foreground = Color.RED
            addActionListener {
                exitProcess(0)
            }
        })
        return this
    }
}

object AboutMenu : JMenu("About") {

    private var isFirstInit = true

    fun init(): AboutMenu {
        return if (isFirstInit) {
            isFirstInit = false
            initialize()
        } else this
    }

    fun initialize(): AboutMenu {
        font = MyStyle.FONT_S
        add(JMenuItem("Release Note").apply {
            font = MyStyle.FONT_S
            addActionListener {
                MyDialog("Release Note",
                    File("src/pers/gargantua/mine_sweep/Release Note.html").readText(),
                    400, 300, false)
            }
        })
        addSeparator()
        add(JMenuItem("Information").apply {
            font = MyStyle.FONT_S
            addActionListener {
                MyDialog(
                    "Information",
                    "<html><body style=\"width: 250px\">"
                            + "<h1>Mine Sweep <br>Kotlin Rebuild Edition<br>Alpha-1.0.1<h1>"
                            + "<p align=\"right\">Power by Gargantua<br>"
                            + "From DGUT.CST"
                            + "</p></body></html>",
                    400, 300, false
                )
            }
        })
        return this
    }
}