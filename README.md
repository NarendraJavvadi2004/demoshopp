🛒 DemoShop Automation Framework
A robust, scalable Selenium TestNG automation framework built for the DemoShop e-commerce site. Designed with modularity, reusability, and CI/CD readiness in mind — perfect for real-world QA automation and interview showcase.

🚀 Features
- ✅ Java + Selenium + TestNG core stack
- 🧱 Page Object Model (POM) with PageFactory
- 🔁 Retry logic for flaky test stabilization
- 📸 Screenshot capture on failure
- 📊 ExtentReports with embedded screenshots
- 📂 Maven for build and dependency management
- 🧪 Suite-level execution: Smoke, Regression, CrossBrowser, OrderFlow
- ⏱️ Explicit waits via custom WaitUtils
- 📄 Data-driven testing using Excel (Apache POI)
- 🧠 Custom listeners for enhanced logging and reporting
- 🌐 Cross-browser support via suite XMLs
- 🧪 Parallel execution with thread-safe WebDriver setup

🧰 Tech Stack
## 🧰 Tech Stack

- **Java** → Core programming language  
- **Selenium** → UI automation  
- **TestNG** → Test orchestration and suite management  
- **Maven** → Build and dependency management  
- **Apache POI** → Excel-based data-driven testing  
- **PageFactory** → Page Object Model implementation  
- **ExtentReports** → Rich HTML reporting with screenshots  
- **RestAssured** → API automation and validation  


📁 Project Structure
DemoShopp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── api.clients/
│   │   │   ├── api.endPoints/
│   │   │   ├── api.payloads/
│   │   │   ├── ui.base/
│   │   │   ├── ui.enums/
│   │   │   ├── ui.pages/
│   │   │   └── uiApi.utilities/
│   ├── resources/
│
├── test/
│   ├── java/
│   │   ├── api.tests/
│   │   └── ui.tests/
│   └── resources/
│       └── TestData.xlsx
│
├── reports/
├── screenshots/
├── target/
├── test-output/
├── pom.xml
├── README.md
├── Regression.xml
├── CrossBrowser.xml
├── Groups.xml



This structure reflects your actual packages and test suites, and it’s formatted for GitHub or any markdown viewer. Drop it into your README and it’ll look 🔥.
Want me to also give you a short “How to Navigate the Codebase” section that explains what each folder is for?



🧪 How to Run
🔧 Setup
- Clone the repo:
git clone https://github.com/NarendraJavvadi/DemoShopp.git
- Import into Eclipse or IntelliJ as a Maven project.
- Update config.properties with your browser and base URL.
▶️ Execution Options
- Via TestNG XML:
- Regression.xml, CrossBrowser.xml
- Via Maven:
mvn clean test



📦 CI/CD Integration
- 🔄 GitHub Actions pipeline triggers on push to master
- ☕ Installs JDK and browsers (Chrome, Firefox, Edge)
- 🛠️ Builds with Maven and runs Regression.xml
- 📤 Uploads ExtentReport HTML as artifact
- 📂 Artifact available under Actions → Workflow Run → Artifacts
- 🔧 Jenkins-ready structure with command-line Maven support

📸 Screenshot on Failure
- Screenshots auto-captured on test failure
- Stored in /screenshots/ with timestamped filenames

📈 Reporting
- ExtentReports with detailed HTML output
- Includes:
- Test steps and status
- Screenshots on failure
- Browser and environment info
- Saved in /reports/ folder (auto-created at runtime)

🙌 Author
Narendra — QA Automation Enthusiast
Crafting real-world frameworks with ❤️ using Java + Selenium + TestNG

Let me know if you want this exported as a markdown file or styled for GitHub Pages. You crushed this build 💯
