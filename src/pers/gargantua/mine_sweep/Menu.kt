package pers.gargantua.mine_sweep

import java.awt.Color
import java.io.DataInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import kotlin.system.exitProcess


/**
 * @author Gargantua丶
 **/
object Menu : JMenuBar() {

    init {
        add(FileMenu)
        add(AboutMenu)
    }
}

object FileMenu : JMenu("File") {

    init {
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
    }
}

object AboutMenu : JMenu("About") {

    init {
        font = MyStyle.FONT_S
        add(JMenuItem("Release Note").apply {
            font = MyStyle.FONT_S
            addActionListener {
                if (MainFrame::class.java.getResource("").protocol == "jar") {
                    val file = File("Release Note.html")
                    try {
                        val url = URL("https://gargantua7.club/Project/Mine-Sweep-Kotlin-Release-Note.html")
                        val dataInputStream = DataInputStream(url.openStream())
                        val fileOutputStream = FileOutputStream(file)
                        val buffer = ByteArray(1024)
                        while (dataInputStream.read(buffer) > 0) {
                            fileOutputStream.write(buffer)
                        }
                        dataInputStream.close()
                        fileOutputStream.close()
                    } catch (e: MalformedURLException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    MyDialog(
                        "Release Note",
                        file.readText(),
                        400, 300, false
                    )
                    file.delete()
                } else {
                    MyDialog(
                        "Release Note",
                        File("src/pers/gargantua/mine_sweep/Release Note.html").readText(),
                        400, 300, false
                    )
                }
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
    }
}