package testsIos;

import actions.AmazonBooksActions;
import base.IosBaseTest;
import entities.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonHomePage;

import java.io.IOException;
import java.util.List;

public class GivenBookIsInTheListOfBooksFromSearchTest extends IosBaseTest {
    @Test(description = "verification that given book is in the list of books from search")
    public void searchGivenBookInTheListOfBooksTest() throws IOException, InterruptedException {
        setUpIos();
        AmazonHomePage amazonHomePage = launchBrowserApplication("https://www.amazon.com/");
        Thread.sleep(3000);
        amazonHomePage.openFilters();
        Thread.sleep(3000);
        amazonHomePage.selectBooksFilter();
        Thread.sleep(3000);
        amazonHomePage.fillUpSearchBar("java");
        Thread.sleep(3000);
        AmazonBooksActions amazonBooksActions = amazonHomePage.clickSearchButton();
        Thread.sleep(3000);
        List<Book> bookList  = amazonBooksActions.collectEachBookInTheListApple();
        Thread.sleep(3000);
        Book givenBook = amazonBooksActions.getGivenBookInfoApple("https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_2?dchild=1&keywords=Java&qid=1610356790&s=books&sr=1-2");
        System.out.println(givenBook);
        System.out.println(bookList.toString());
        Assert.assertTrue(bookList.contains(givenBook), "Your book with name"+givenBook.getProductName()+" was not found in Amazon list");
    }
}
