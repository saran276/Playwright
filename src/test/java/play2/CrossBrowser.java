package play2;

import java.nio.file.Paths;
import java.util.Iterator;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CrossBrowser {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
		}
		String[] browserNames = { "Chromium", "Firefox", "WebKit" };
		BrowserType[] browsers = { Playwright.create().chromium(), Playwright.create().firefox(),
				Playwright.create().webkit() };
		for (int i = 0; i < browsers.length; i++) {
			String browserName = browserNames[i];
			BrowserType browserType = browsers[i];

			System.out.println("Testing on : " + browserName);

			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://flowcv.com/");
			
			String pageTitle = page.title();
			if (!"flowcv".equalsIgnoreCase(pageTitle)) {
				System.out.println("Test passed on " + browserName);
			} else {
				System.out.println("Test failed on " + browserName);
			}
			
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(browserName + "_screenshot.png")));
			browser.close();
			}
			
		} 

	}


