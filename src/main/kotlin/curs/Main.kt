package curs

import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val lifeUi = LifeUi()
        lifeUi.isVisible = true
    }
}