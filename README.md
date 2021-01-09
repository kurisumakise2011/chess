### Preferences

1. [About](#about)
2. [Gameplay](#gameplay)
3. [Build and execution](#build-and-execution)


### About


Simple single-player the chess computer game is written in Java

All game actions such as: movement, check, checkmate, pawn promotion, castling, en passant are implemented.

This open-source project can be used for any purpose, university task, self-development, the base for more complex projects.

Pull requests or issues are welcome.


### Gameplay


*Chess board overview*

Topalov vs Kasparov 1999

![Chessboard overview](https://github.com/kurisumakise2011/chess/raw/master/readme/chessboard.png)

*Movement*

To move click on any piece then click where to move.

Be aware, you cannot move in case of check, only moves regarding to the game rules are possible.

![Movement](https://github.com/kurisumakise2011/chess/raw/master/readme/movement.png)

*Attack*

To attack click on an enemy piece

![Attack](https://github.com/kurisumakise2011/chess/raw/master/readme/struggle.png)

*Check*

If the king got check, player must avoid it.

![Check](https://github.com/kurisumakise2011/chess/raw/master/readme/check.png)

*Checkmate*

If a player could not avoid a check, it's checkmate, the finish of game.

![Checkamte](https://github.com/kurisumakise2011/chess/raw/master/readme/checkmate.png)


*Castling*

Castling can be performed when:

* The king piece has not moved yet.
* A Castling rock, has not moved yet as well
* The castling path is not under attack
* The king is not under check.

![Castling](https://github.com/kurisumakise2011/chess/raw/master/readme/castling_1.png)

![Castling](https://github.com/kurisumakise2011/chess/raw/master/readme/castling_2.png)

[More detailed](https://en.wikipedia.org/wiki/Castling)


*Pawn promotion*


The situation when a pawn achieves last rank on the desk. 
For black pawns this is the eighth rank, for white is the first.

![Pawn promotion. Options window](https://github.com/kurisumakise2011/chess/raw/master/readme/promotion_1.png)

In that case, queen had been chosen.

![Pawn promotion. Queen chosen](https://github.com/kurisumakise2011/chess/raw/master/readme/promotion_2.png)

*En passant (in passing)*

The interesting situation when piece taking enemy pawn after its first move.
Only possible for pawns. If instead of passing the player moves other piece, passing will be lost

![En Passant](https://github.com/kurisumakise2011/chess/raw/master/readme/passant_1.png)

![White's taking an enemy piece](https://github.com/kurisumakise2011/chess/raw/master/readme/passant_2.png)

[More detailed](https://en.wikipedia.org/wiki/En_passant)


### Build and execution

#### Requirements

* Java 11+
* JavaFX SDK 11+

#### Build

Maven build is convenient form to deploy, build and run an executable jar.


#### Console

Embedded javafx sdk added for simplicity (after 9 java release version, javafx is not a part of JDK anymore). To run the compiled project, add the following VM option to command arguments line.

```--module-path sdk/javafx-sdk-11.0.2/lib --add-modules javafx.base,javafx.graphics,javafx.controls```

