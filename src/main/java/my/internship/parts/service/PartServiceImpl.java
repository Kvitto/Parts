package my.internship.parts.service;

import my.internship.parts.dao.PartDao;
import my.internship.parts.model.Part;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service //помечаем класс что он является сервисным
public class PartServiceImpl implements PartService {
    private PartDao partDao;
    private int option = 0; // параметр дополнительных операций: 0-без доп, 1-поиск по searchTitle, 2...-сортировка.
    private String searchTitle;
    private int strPerPage = 10;
    private int noPage = 0;
    private int totalPages = 1;



    @Transactional
    public void setNoPage(int noPage) { //сюда закидываем номер страницы который нужно вернуть в листе
        this.noPage = noPage;
    }

    @Transactional
    public int getNoPage() {
        return noPage;
    }

    @Transactional
    public int getTotalPages() { //отсюда будем забирать количество страниц в листе
        return totalPages;
    }

    @Transactional
    public void setOption(int option) {
        this.option = option;
    }

    @Transactional
    public int getOption() {
        return option;
    }

    @Transactional
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public void setPartDao(PartDao partDao) {
        this.partDao = partDao;
    }

    @Transactional
    public void addPart(Part part) {
        this.partDao.addPart(part);
    }

    @Transactional
    public void updatePart(Part part) {
        this.partDao.updatePart(part);
    }

    @Transactional
    public void removePart(int id) {
        this.partDao.removePart(id);
    }

    @Transactional
    public Part getPartById(int id) {
        return this.partDao.getPartById(id);
    }

    @Transactional
    public List<Part> listParts() {
        List<Part> list = new ArrayList<Part>();
        switch (option) {
            case 0:
                list = this.partDao.listParts();
                break;
            case 1:
                list = this.partDao.listParts(searchTitle);
                break;
            case 2:
                list = this.partDao.listSort(option);
                break;
            case 3:
                list = this.partDao.listSort(option);
                break;
            default:
                list = this.partDao.listParts();
                break;
        }
        totalPages = list.size()==0?0:list.size()%strPerPage==0?list.size()/strPerPage-1:list.size()/strPerPage;
        if(noPage>totalPages)noPage=new Integer(totalPages);

        return list.subList(strPerPage*noPage, noPage!=totalPages? strPerPage*noPage+strPerPage:list.size());
    }

    @Transactional
    public int getMinBasePart() {
        return this.partDao.getMinBasePart();
    }


}
