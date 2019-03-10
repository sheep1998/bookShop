package com.example.bookshop.Service.ServiceImpl;

import com.example.bookshop.DAO.BookRepository;
import com.example.bookshop.DAO.OrderitemRepository;
import com.example.bookshop.Model.Book;
import com.example.bookshop.Model.Orderitem;
import com.example.bookshop.Service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderitemServiceImpl implements OrderitemService {
    @Autowired
    private OrderitemRepository orderitemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<HashMap<String,String>> queryItems(int order_id) {
        List<Orderitem> items = orderitemRepository.findAllByOrderId(order_id);
        List<HashMap<String,String>> hashMaps = new LinkedList<>();
        Iterator itor = items.iterator();
        while (itor.hasNext()){
            HashMap<String,String> hashMap = new HashMap<>();
            Orderitem orderitem = (Orderitem) itor.next();
            hashMap.put("orderitemId",String.valueOf(orderitem.getItemId()));
            hashMap.put("number",orderitem.getNumber().toString());
            hashMap.put("tprice",orderitem.getTprice().toString());
            Integer bookId = orderitem.getBookId();
            Optional<Book> book = bookRepository.findBookByCoding(bookId);
            hashMap.put("bookId",String.valueOf(book.get().getCoding()));
            hashMap.put("name",book.get().getName());
            hashMap.put("img",book.get().getImgs());
            hashMaps.add(hashMap);
        }
        return hashMaps;
    }
}
