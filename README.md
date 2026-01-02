🛒 DemoShop Automation Framework
A robust, scalable Selenium TestNG automation framework built for the DemoShop e-commerce site. Designed with modularity, reusability, and CI/CD readiness in mind — perfect for real-world QA automation and interview showcase.
🚀 Features
- ✅ Java + Selenium + TestNG core stack
- 🧱 Page Object Model (POM) with PageFactory
- 🔁 Retry logic for flaky test handling
- 📸 Screenshot capture on failure
- 📊 TestNG Extent Reports and screenshots
- 📂 Maven for build and dependency management
- 🧪 Smoke, Regression, Cross-browser suite support
- ⏱️ Explicit waits for stable element interaction
- 📄 Data-driven testing via Excel (Apache POI)
- 🧠 Custom listeners for enhanced reporting
- 🌐 Cross-browser execution via XML suite
- 🧪 Parallel execution with thread-safe WebDriver
🧰 Tech Stack
  Java
  Selenium
  TestNG
  Maven
  ApachePOI
  PageFactory


📁 Project Structure
DemoShopFramework/
├── src/
│   ├── base/           # WebDriver setup, config
│   ├── pages/          # Page classes (POM)
│   ├── tests/          # Test classes
│   ├── utils/          # Helpers: Excel, waits, screenshots
│   └── listeners/      # Retry, ITestListener
├── testng.xml          # Suite config
├── pom.xml             # Maven config
└── README.md           # Project overview


🧪 How to Run
- Clone the repo:
git clone https://github.com/your-username/DemoShopFramework.git
- Import into Eclipse/IntelliJ as a Maven project.
- Update config.properties with your browser and URL.
- Run via:
- TestNG XML (testng.xml)
- Maven:
mvn clean test


📦 CI/CD Ready
- Framework is structured for Jenkins integration.
- Supports command-line execution via Maven.
- Easily extendable for GitHub Actions or other CI tools.
📸 Sample Screenshot on Failure
Screenshots are auto-captured and stored in /screenshots with timestamped filenames.
📈 Reporting
- TestNG default reports
🙌 Author
Narendra — QA Automation Enthusiast  Java + Selenium  Building real-world frameworks with ❤️
