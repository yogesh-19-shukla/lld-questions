
---

## 🔁 Game Flow

1. **Game Initialization**
    - Game takes in board size and player list
    - Initializes `Board` and `WinningStrategy`

2. **Main Game Loop**
    - While status is `IN_PROGRESS`:
        - Get move from current player
        - Validate move (bounds + empty cell)
        - Apply move to board
        - Check for winner using `WinningStrategy`
        - If no winner, switch player
        - If board is full, mark as `DRAW`

3. **Win Checking**
    - Implemented using Strategy pattern
    - `DefaultWinningStrategy` checks:
        - Row
        - Column
        - Diagonal
        - Anti-diagonal

---

## 🔍 Class Responsibilities

### `Symbol` & `GameStatus` (Enum)
- Holds player marks (`X`, `O`)
- Tracks game state (`IN_PROGRESS`, `WIN`, `DRAW`)

### `Cell`
- Represents each grid position
- Stores `Player` who has marked it

### `Move`
- Contains player intent: `row`, `col`, and `Player`

### `Player` (Abstract) + `HumanPlayer`
- Interface for move-making logic
- Allows future extension with `BotPlayer`

### `Board`
- Holds the actual game grid (`Cell[][]`)
- Validates and applies moves
- Prints the current board

### `WinningStrategy` (Interface)
- Allows plugging in different win-checking logic
- Decouples rule engine from core game

### `DefaultWinningStrategy`
- Implements traditional Tic Tac Toe win logic
- Can be replaced with custom rules easily

### `Game`
- Central orchestrator class
- Manages players, board, move loop, win/draw check

---

