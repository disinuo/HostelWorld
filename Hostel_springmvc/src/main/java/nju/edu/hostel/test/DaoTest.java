//package nju.edu.hostel.test;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import nju.edu.hostel.dao.VIPDao;
//import nju.edu.hostel.model.BookBill;
//import nju.edu.hostel.model.Vip;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by disinuo on 17/3/5.
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//public class SpringMvcTestLoginControllerTest {
//    @Before
//    public void setup() throws Exception {
//        this.account = new AccountBuilder() {
//            {
//                address("Herve", "4650", "Rue de la station", "1", null, "Belgium");
//                credentials("john", "secret");
//                name("John", "Doe");
//            }
//        }.build(true);
//
//        Mockito.when(this.accountService.login("john", "secret")).thenReturn(this.account);
//    }
//    @Autowired
//    VIPDao vipDao;
//    @Configuration
//    static class LoginControllerTestConfiguration {
//
//        @Bean
//        public AccountService accountService() {
//            return Mockito.mock(AccountService.class);
//        }
//
//        @Bean
//        public LoginController loginController() {
//            return new LoginController();
//        }
//    }
//    @Autowired
//    private LoginController loginController;
//
//    @Autowired
//    private AccountService accountService;
//}
////public class DaoTest extends BaseTest{
////    @Configuration
////    static class LoginControllerTestConfiguration {
////
////        @Bean
////        public AccountService accountService() {
////            return Mockito.mock(AccountService.class);
////        }
////    @Autowired
////    VIPDao vipDao;
////
////    @Test
//////    @Transactional   //标明此方法需使用事务
////    public void test1(){
////        Vip vip=vipDao.getById(1000000);
////        List<BookBill> bookBills=vip.getBookBills();
////        if(bookBills!=null){
////            for(BookBill bill:bookBills){
////                System.out.println(bill.getId()+" "+bill.getHostel().getName());
////            }
////        }else {
////            System.out.println("没有预订记录");
////        }
////    }
////
////}
