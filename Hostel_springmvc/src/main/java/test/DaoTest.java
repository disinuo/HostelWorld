package test;
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

import nju.edu.hostel.dao.*;
import nju.edu.hostel.model.BookBill;
import nju.edu.hostel.model.Vip;
import nju.edu.hostel.service.VIPService;
import nju.edu.hostel.service.bean.VIPServiceBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={ "classpath:/mvc-dispatcher-servlet.xml"})
public class DaoTest {
    private MockMvc mockMvc;


//    @Mock
//    BaseDao baseDao;
    @Mock
    VIPDao vipDao;//=new VIPDaoImpl();
    @Mock
    BookBillDao bookBillDao;
    @Mock
    HostelDao hostelDao;
    @Mock
    UserDao userDao;
//
    @InjectMocks
    VIPService vipService=new VIPServiceBean();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @Rollback
    public void should_return_success_when_add_recipient_not_exist() throws Exception {
        List<BookBill>bills= vipService.getAllBookBills(1000000);
        for(BookBill bill:bills){
            System.out.println(bill.getId()+" "+bill.getLiveInDate()+" "+bill.getHostel()+" "+bill.getRoom());
        }
//        System.out.print(vip.getId()+" "+vip.getRealName());

//        when(vipService.getAllBookBills(1000000)).thenReturn(null);
//        when(recipientRepository.save(any(Recipient.class))).thenReturn(null);

//        assertThat(recipientService.add("Tom", "test@test.com").getStatus(), is("成功"));
//        verify(recipientRepository).findByEmail(anyString());
//        verify(recipientRepository).save(any(Recipient.class));
    }



    @Test
    public void test() throws Exception {
//        when(mailService.send("test@test.com", "test", "test")).thenReturn(new Result("成功"));
//
//        mockMvc.perform(post("/mail/send")
//                .param("recipients", "test@test.com")
//                .param("subject", "test")
//                .param("content", "test"))
//                .andDo(print())
//                .andExpect(status().isOk()).andExpect(content().string(is("{\"status\":\"" + result + "\"}")));
//
//        verify(mailService).send("test@test.com", "test", "test");

        Vip vip=vipDao.get(1000000);
//        for(LiveBill bill:bills){
//            System.out.println(bill.getId()+" "+bill.getDate()+" "+bill.getHostel()+" "+bill.getRoom());
//        }
        System.out.print(vip.getId()+" "+vip.getRealName());
    }
}