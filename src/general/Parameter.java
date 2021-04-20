package general;

public class Parameter {
	public static final String sep = System.getProperty("file.separator");
	public static final String projectdir = System.getProperty("user.dir");

	// path to different folders
	public static final String resourcesPath = projectdir + sep + "resources" + sep;
	public static final String imagesPath = resourcesPath + "images";

	// path to different files
	public static final String musicImage = imagesPath + sep + "music.png";
	public static final String movieImage = imagesPath + sep + "movie.png";
	public static final String geographyImage = imagesPath + sep + "geography.png";
	public static final String xmlPath = resourcesPath + "data.xml";
	public static final String databasePath = resourcesPath + "quiz.db";

	// panel width
	public static final int P_WIDTH = 700;;
}
