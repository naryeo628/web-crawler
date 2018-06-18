import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by roshanalwis on 9/4/17.
 */

public class Crawler {

	public static void main(String[] args) {
		int i;
		System.setProperty("webdriver.chrome.driver", "..\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize browser window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://sports.news.naver.com/wfootball/schedule/index.nhn?category=russia2018");
		List<WebElement> btn = driver.findElements(By.xpath("(//a[contains(text(),'전력비교')])"));

		for (i = 0; i < btn.size(); i++) {

			driver.findElement(By.xpath("(//a[contains(text(),'전력비교')])[" + Integer.toString(i + 2) + "]")).click();
			i++;
			String leftTeamRanking = driver
					.findElement(By.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li/div/div/div/em"))
					.getText();
			if (leftTeamRanking.equals("-"))
				break;
			String leftTeam = driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/div[2]/strong"))
					.getText();
			String rightTeam = driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div[2]/div[2]/strong"))
					.getText();

			String rightTeamRanking = driver
					.findElement(
							By.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li/div/div/div[2]/em"))
					.getText();
			String leftTeamParticipants = driver
					.findElement(
							By.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[2]/div/div/div/em"))
					.getText();
			String rightTeamParticipants = driver
					.findElement(By
							.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[2]/div/div/div[2]/em"))
					.getText();
			String leftTeamBest = driver
					.findElement(
							By.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[3]/div/div/div/em"))
					.getText();
			String rightTeamBest = driver
					.findElement(By
							.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[3]/div/div/div[2]/em"))
					.getText();
			String leftTeam2014 = driver
					.findElement(
							By.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[4]/div/div/div/em"))
					.getText();
			String rightTeam2014 = driver
					.findElement(By
							.xpath("//div[@id='content']/div/div[3]/div[3]/div/div/div[2]/ul/li[4]/div/div/div[2]/em"))
					.getText();
			System.out.println(leftTeam + "		vs	" + rightTeam);
			System.out.println(leftTeamRanking + "		vs	" + rightTeamRanking);
			System.out.println(leftTeamParticipants + "		vs	" + rightTeamParticipants);
			System.out.println(leftTeamBest + "		vs	" + rightTeamBest);
			System.out.println(leftTeam2014 + "		vs	" + rightTeam2014);
			System.out.println("");
			driver.get("https://sports.news.naver.com/wfootball/schedule/index.nhn?category=russia2018");
		}
		System.out.println(i);
		// Find the table element using xpath
		// WebElement table =
		// driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/table"));
		//
		// // Go through each major version
		// List<WebElement> mainVersions = table.findElements(By.tagName("tbody"));
		//
		// for (WebElement mver : mainVersions) {
		// for (WebElement ver : mver.findElements(By.tagName("tr"))) {
		// // Get all anchor tags
		// List<WebElement> attributes = ver.findElements(By.tagName("a"));
		//
		// // Find each relevant web element that contains required information
		// WebElement version = attributes.get(0);
		// WebElement repository = attributes.get(1);
		// WebElement usages = attributes.get(2);
		// WebElement date = Iterables.getLast(ver.findElements(By.tagName("td")));
		//
		// System.out.println("Version : " + version.getText());
		// System.out.println("Repository : " + repository.getText());
		// System.out.println("Usages : " + usages.getText());
		// System.out.println("Date : " + date.getText());
		// System.out.println("------------------------------");
		// }
		//
		// }

		// Close driver
		driver.quit();
	}
}