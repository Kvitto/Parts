package my.internship.parts.dao;

import my.internship.parts.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository //помечаем что данный класс является репозиторием
public class PartDaoImpl implements PartDao{
    private static final Logger logger = LoggerFactory.getLogger(PartDaoImpl.class); //
    private SessionFactory sessionFactory; //Сущность для созданя подключения к базе данных

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPart(Part part) {
        Session session = this.sessionFactory.getCurrentSession();//Создаем сессию которая просит нашу фабрику сессий получить текущую сессию
        session.persist(part);  //сессия сохраняет объект который мы ей передали
        logger.info("Part successfully saved. Part details: "+part); //Логируем информацию
    }

    public void updatePart(Part part) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(part);//изменяем наш объект
        logger.info("Part successfully update. Part details: "+part);
    }

    public void removePart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, new Integer(id));

        if(part!=null){
            session.delete(part);
        }
        logger.info("Part successfully removed. Part details: " + part);
    }

    public Part getPartById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, new Integer(id));
        logger.info("Part successfully loaded. Part details: " + part);
        return part;
    }
    @SuppressWarnings("unchecked")
    public List<Part> listParts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Part> partList = session.createQuery("FROM Part").list();
        return partList;
    }
    @SuppressWarnings("unchecked")
    public List<Part> listParts(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println(String.format("FROM Part where partName=%s", search));
        return (List<Part>) session.createQuery(String.format("FROM Part where partName='%s'", search)).list();
    }
    @SuppressWarnings("unchecked")
    public List<Part> listSort(int opt) {
        Session session = this.sessionFactory.getCurrentSession();
        switch (opt){
            case 2:
                return (List<Part>) session.createQuery(String.format("FROM Part where partBase=%s", 1)).list();
            case 3:
                return (List<Part>) session.createQuery(String.format("FROM Part where partBase=%s", 0)).list();
        }
        return null;
    }

    public int getMinBasePart() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Part> listP = session.createQuery("from Part").list();
        Part part = null;
        for(Part prt : listP) {
            if (prt.isPartBase()) {
                part = prt;
                break;
            }
        }

        if(part==null) return 0;

        for(Part prt : listP) {
            if (prt.isPartBase()) {
                if (prt.getPartValue() < part.getPartValue()) {
                    part = prt;
                }
            }
        }

        return part.getPartValue();
    }
}
