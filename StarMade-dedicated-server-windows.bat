IF NOT EXIST "./StarMade" (
	ECHO "Please run the StarMade Launcher at least once before starting a server."
	ECHO "The launcher will create the necessary folders and files for the server to run."
	PAUSE
	EXIT
)
java -jar ./app/StarMade-Launcher.jar -server -port: 4242