import java.sql._
import scala.collection.mutable.ArrayBuffer

object Main extends App {
  var con: Connection = _

  val moviesAB = new ArrayBuffer[(String, String, String)]()
  val connectionString = "jdbc:hive2://localhost:10000/movies"
  Class.forName("org.apache.hive.jdbc.HiveDriver")
  con = DriverManager.getConnection(connectionString, "", "")

  val statement = con.createStatement()

  try {

    var cmd = ""
    val formatter = java.text.NumberFormat.getIntegerInstance
    while (cmd != "q"){
      println("What would you like to do?")
      cmd = scala.io.StdIn.readLine()
      cmd match{
        case "q" => println("Exiting")
        case "1" => highestRatingGiven()
        case "2" => numGreatMovies()
        case "3" => numBadMovies()
        case "4" => worstMovies(50)
        case "5" => greatMovies(50)
        case _ => println("Invalid Selection")
      }
    }


  } catch {
    case e: Exception => e.printStackTrace()
  }

  con.close();

  /**
   * number of 5 star movies
   */
  def numGreatMovies() = {
    var numMovies = 0
    var result = statement.executeQuery(
      "SELECT * FROM ratings " )
    while (result.next){
      if (result.getInt("rating")== 5) numMovies +=1
    }
    println("Number of 5 star movies: "+ numMovies)
  }

  /**
   * Number of movies rated 1 star or less
   */
  def numBadMovies() = {
    var numMovies = 0
    var result = statement.executeQuery(
      "SELECT * FROM ratings ")
    while (result.next){
      if (result.getInt("rating")<= 1) numMovies +=1
    }
    println("Number of terrible movies: "+ numMovies)
  }

  /**
   * Display worst movies
   * @param show
   */
  def worstMovies(show: Int) = {
    var resultSet = statement.executeQuery(
      "SELECT * FROM ratings "+
        "ORDER BY rating"
    )
    var counter = 0
    while (resultSet.next && counter < show+1) {
      val userId = resultSet.getString("userId")
      val movieId = resultSet.getString("movieId")
      val rating = resultSet.getString("rating")
      val r = (userId, movieId, rating)
      moviesAB += r
      counter += 1
    }
    moviesAB foreach println
  }

  /**
   *
   */
  def highestRatingGiven() = {
    var highestRatedMovies = statement.executeQuery(
      "SELECT MAX(rating) AS highest_ratings " +
        "FROM ratings "
    )

    highestRatedMovies.next
    val highestRated = highestRatedMovies.getInt("highest_ratings")
    println("Highest Rated Movie: " +highestRated)
  }

  /**
   * Display movies rated 5 stars
   * @param show
   */
  def greatMovies(show: Int) ={
    val moviesAB = new ArrayBuffer[(String, String, String)]()
    var resultSet = statement.executeQuery(
      "SELECT * FROM  ratings WHERE rating = 5 " +
        "ORDER BY userId"
    )
    var counter = 0
    while (resultSet.next && counter < show+1) {
      val userId = resultSet.getString("userId")
      val movieId = resultSet.getString("movieId")
      val rating = resultSet.getString("rating")
      val r = (userId, movieId, rating)
      moviesAB += r
      counter += 1
    }
    moviesAB foreach println
  }

}