package curs


import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import javax.swing.*

private const val GAP = 1

class LifeUi : JFrame("Game of life"), ModelChangeListener {
    private var gameModel: Universe
    private var gameModelSize: Int
    private val startButton: JButton
    private lateinit var timer: Timer

    private val buttons = mutableListOf<MutableList<JButton>>()

    init {
        setSize(700, 700)
        defaultCloseOperation = EXIT_ON_CLOSE
        val dialogOption = JOptionPane.showConfirmDialog(
            this,
            "Do you want to manually switch states?",
            "Options for show",
            JOptionPane.YES_NO_OPTION
        )
        if (dialogOption == JOptionPane.OK_OPTION) {
            gameModelSize = JOptionPane.showInputDialog("Universe size?\n(min=5, max=40)").toInt()
            if (gameModelSize < 5) {
                gameModelSize = 5
            }
            if (gameModelSize > 40) {
                gameModelSize = 40
            }
            gameModel = Universe(gameModelSize)
            startButton = createStartButton()
            rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
                add(startButton, BorderLayout.NORTH)
                add(createBoardPanel(), BorderLayout.CENTER)
                add(createRestartButton(), BorderLayout.SOUTH)
                border = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)
            }
        } else {

            gameModelSize = JOptionPane.showInputDialog("Universe size?\n(min=5, max=40)").toInt()
            if (gameModelSize < 5) {
                gameModelSize = 5
            }
            if (gameModelSize > 40) {
                gameModelSize = 40
            }
            gameModel = Universe(gameModelSize)

            timer = Timer(
                1000
            ) {
                gameModel.evolve()
                updateGameUI()
            }
            startButton = createStartButtonForAuto()
            rootPane.contentPane = JPanel(BorderLayout(GAP, GAP)).apply {
                add(startButton, BorderLayout.NORTH)
                add(createBoardPanel(), BorderLayout.CENTER)
                add(createRestartButtonForAuto(), BorderLayout.SOUTH)
                border = BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP)
            }
        }
        resubscribe()
    }

    private fun createRestartButton(): Component {
        val restartButton = JButton("Restart")
        updateFont(restartButton, 20.0f)
        restartButton.addActionListener {
            val dialogOption = JOptionPane.showConfirmDialog(
                this,
                "Are you sure?",
                "Restart",
                JOptionPane.OK_CANCEL_OPTION
            )
            if (dialogOption == JOptionPane.OK_OPTION) {
                resubscribe()
                startButton.text = "Start"
            }
        }
        return restartButton
    }

    private fun createRestartButtonForAuto(): Component {
        val restartButton = JButton("Restart")
        updateFont(restartButton, 20.0f)
        restartButton.addActionListener {
            val dialogOption = JOptionPane.showConfirmDialog(
                this,
                "Are you sure?",
                "Restart",
                JOptionPane.OK_CANCEL_OPTION
            )
            if (dialogOption == JOptionPane.OK_OPTION) {
                resubscribe()
                startButton.text = "Start"
                timer.stop()
            }
        }
        return restartButton
    }

    private fun createStartButton(): JButton {
        val startButton = JButton("Start")
        updateFont(startButton, 20.0f)
        startButton.addActionListener {
            if (startButton.text == "Start") {
                val dialogOption = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure?",
                    "Start",
                    JOptionPane.OK_CANCEL_OPTION
                )
                if (dialogOption == JOptionPane.OK_OPTION) {
                    offAllCells()
                    startButton.text = "Next"
                }
            } else {
                gameModel.evolve()
                updateGameUI()
            }
        }
        return startButton
    }

    private fun createStartButtonForAuto(): JButton {
        val startButton = JButton("Start")
        updateFont(startButton, 20.0f)
        startButton.addActionListener {
            when (startButton.text) {
                "Start" -> {
                    val dialogOption = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure?",
                        "Start",
                        JOptionPane.OK_CANCEL_OPTION
                    )
                    if (dialogOption == JOptionPane.OK_OPTION) {
                        offAllCells()
                        timer.start()
                        startButton.text = "Pause"
                    }
                }
                "Pause" -> {
                    timer.stop()
                    startButton.text = "Continue"
                }
                else -> {
                    timer.start()
                    startButton.text = "Pause"
                }
            }
        }
        return startButton
    }

    private fun offAllCells() {
        for (buttonRow in buttons) {
            for (button in buttonRow) {
                button.isEnabled = false
            }
        }
    }

    private fun onAllCells() {
        for (buttonRow in buttons) {
            for (button in buttonRow) {
                button.isEnabled = true
            }
        }
    }

    private fun resubscribe() {
        gameModel.removeModelChangeListener(this)
        gameModel = Universe(gameModelSize)
        gameModel.addModelChangeListener(this)
        updateGameUI()
        onAllCells()
    }

    private fun createBoardPanel(): Component {
        val gamePanel = JPanel(GridLayout(gameModelSize, gameModelSize, GAP, GAP))

        for (i in 0 until gameModelSize) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 0 until gameModelSize) {
                val cellButton = JButton("")
                cellButton.addActionListener {
                    if (gameModel.cellAt(i, j).isAlive) {
                        gameModel.cellAt(i, j).dead()
                    } else {
                        gameModel.cellAt(i, j).live()
                    }
                    updateGameUI()
                }
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
                updateFont(cellButton, 30.0f)
            }
            buttons.add(buttonsRow)
        }
        return gamePanel
    }

    private fun updateFont(component: JComponent, newFontSize: Float) {
        val font = component.font
        val derivedFont = font.deriveFont(newFontSize)
        component.font = derivedFont
    }

    override fun onModelChanged() {
        updateGameUI()
    }

    private fun updateGameUI() {
        for ((i, buttonRow) in buttons.withIndex()) {
            for ((j, button) in buttonRow.withIndex()) {
                val cell = gameModel.cellAt(i, j)
                if (cell.isAlive) {
                    button.background = Color.BLACK
                } else {
                    button.background = Color.WHITE
                }
            }
        }
    }
}