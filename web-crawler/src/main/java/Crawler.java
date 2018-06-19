import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Crawler {

	static SearchWord[] words = new SearchWord[20];

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "..\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize browser window
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		int i, j, interestPerWord;
		String temp;

		driver.get("https://www.facebook.com/");
		try {
			driver.findElement(By.id("email")).click();
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys("01075774937");
			driver.findElement(By.id("pass")).click();
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys("wjddnr4937");
			driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			// TODO: handle exception
		}
		driver.get("https://datalab.naver.com/keyword/realtimeList.naver?where=main");
		for (i = 0; i < 20; i++) {
			words[i] = new SearchWord();
			words[i].setRank(i + 1);
			words[i].setWord(driver.findElement(By.xpath("//div[@id='content']/div/div[3]/div/div/div[4]/div/div/ul/li["
					+ Integer.toString(i + 1) + "]/a/span")).getText());
		}

		driver.get("https://www.facebook.com/");
		for (i = 0; i < 20; i++) {
			(new WebDriverWait(driver, 30))
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("q"))));
			while (true) {
				try {
					driver.findElement(By.name("q")).click();
					break;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			interestPerWord = 0;
			// System.out.println(words[i].getWord());
			driver.findElement(By.name("q")).sendKeys(words[i].getWord());
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<WebElement> interestElements = driver.findElements(By.className("_4arz"));
			for (j = 0; j < interestElements.size(); j++) {
				try {
					temp = interestElements.get(j).getText();
					temp = temp.replaceAll("명", "").replaceAll(",", "");
					if (temp.contains("천")) {
						interestPerWord += Double.parseDouble(temp.replaceAll("천", "")) * 1000;
					} else if (temp.contains("만")) {
						interestPerWord += Double.parseDouble(temp.replaceAll("만", "")) * 10000;
					} else {
						interestPerWord += Double.parseDouble(temp);
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					continue;
				}
			}
			interestElements = driver.findElements(By.className("_36_q"));
			for (j = 0; j < interestElements.size(); j++) {
				try {
					temp = interestElements.get(j).getText();
					if (temp.contains("조회")) {
						continue;
					}
					temp = temp.replaceAll("\\s", "").replaceAll("댓글", "").replaceAll("공유", "").replaceAll("개", "")
							.replaceAll("회", "").replaceAll(",", "");
					if (temp.contains("천")) {
						interestPerWord += Double.parseDouble(temp.replaceAll("천", "")) * 1000;
					} else if (temp.contains("만")) {
						interestPerWord += Double.parseDouble(temp.replaceAll("만", "")) * 10000;
					} else {
						interestPerWord += Double.parseDouble(temp);
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					continue;
				}
			}

			words[i].addDegree(interestPerWord);
		}

		for (i = 0; i < 20; i++) {
			System.out.println(
					"검색어 " + words[i].getRank() + "위	: " + words[i].getWord() + "    관심도 : " + words[i].getDegree());
		}

		// Close driver
		driver.quit();
	}
}
