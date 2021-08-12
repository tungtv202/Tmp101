package me.tungexplorer.study;

interface WebDriver {
    void getElement();

    void selectElement();
}

class ChromeDriver implements WebDriver {
    @Override
    public void getElement() {
        System.out.println("Get element from ChromeDriver");
    }

    @Override
    public void selectElement() {
        System.out.println("Select element from ChromeDriver");
    }
}

class IEDriver {
    public void findElement() {
        System.out.println("Find element from IEDriver");
    }

    public void clickElement() {
        System.out.println("Click element from IEDriver");
    }
}

class IEWebDriverAdapter implements WebDriver {
    final IEDriver ieDriver;

    IEWebDriverAdapter(IEDriver ieDriver) {
        this.ieDriver = ieDriver;
    }

    @Override
    public void getElement() {
        ieDriver.findElement();
    }

    @Override
    public void selectElement() {
        ieDriver.clickElement();
    }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        IEDriver ieDriver = new IEDriver();
        WebDriver webDriverViaAdapter = new IEWebDriverAdapter(ieDriver);
        WebDriver webDriverViaChromeDriver = new ChromeDriver();
    }
}