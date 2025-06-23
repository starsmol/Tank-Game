package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, firePressed;

    // to manage pausing
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            playState(code);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.mainTitleState) {
            mainState(code);
        } else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
    }
    // funkcje obsługi keyPressed dla poszczególnych gameState
    public void playState(int code) {
        if (code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            firePressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum --;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = gp.ui.maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum --;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = gp.ui.maxCommandNum;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum ++;
            if (gp.ui.commandNum > gp.ui.maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum ++;
            if (gp.ui.commandNum > gp.ui.maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == gp.ui.resumeCommandNum) {
                gp.gameState = gp.playState;
            } else if (gp.ui.commandNum == gp.ui.restartCommandNum) {
                gp.resetGame();
                gp.gameState = gp.playState;
            } else if (gp.ui.commandNum == gp.ui.exitCommandNum) {
                gp.gameState = gp.mainTitleState;
            }
        }
    }

    public void mainState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.titleCommandNumber --;
            if (gp.ui.titleCommandNumber < 1) {
                gp.ui.titleCommandNumber = gp.ui.maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_UP) {
            gp.ui.titleCommandNumber --;
            if (gp.ui.titleCommandNumber < 1) {
                gp.ui.titleCommandNumber = gp.ui.maxCommandNum;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.titleCommandNumber ++;
            if (gp.ui.titleCommandNumber > gp.ui.maxCommandNum) {
                gp.ui.titleCommandNumber = 1;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.titleCommandNumber ++;
            if (gp.ui.titleCommandNumber > gp.ui.maxCommandNum) {
                gp.ui.titleCommandNumber = 1;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.titleCommandNumber == gp.ui.restartCommandNum) {
                gp.resetGame();
                gp.gameState = gp.playState;
            } else if (gp.ui.titleCommandNumber == gp.ui.exitCommandNum) {
                System.exit(0);
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.titleCommandNumber --;
            if (gp.ui.titleCommandNumber < 1) {
                gp.ui.titleCommandNumber = gp.ui.maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_UP) {
            gp.ui.titleCommandNumber --;
            if (gp.ui.titleCommandNumber < 1) {
                gp.ui.titleCommandNumber = gp.ui.maxCommandNum;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.titleCommandNumber ++;
            if (gp.ui.titleCommandNumber > gp.ui.maxCommandNum) {
                gp.ui.titleCommandNumber = 1;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.titleCommandNumber ++;
            if (gp.ui.titleCommandNumber > gp.ui.maxCommandNum) {
                gp.ui.titleCommandNumber = 1;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.titleCommandNumber == gp.ui.restartCommandNum) {
                gp.resetGame();
                gp.gameState = gp.playState;
            } else if (gp.ui.titleCommandNumber == gp.ui.exitCommandNum) {
                gp.gameState = gp.mainTitleState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            firePressed = false;
        }
    }
}
