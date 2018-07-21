dir /s /B *.java > sources.txt
IF EXIST bin (
	rmdir bin /S /Q 
)
mkdir bin && javac -d bin @sources.txt && java -cp bin com.hiral2.game.Main