package my.internship.parts.service;

import my.internship.parts.model.Part;

import java.util.List;

public interface PartService {

    public void addPart(Part part);
    public void updatePart(Part part);
    public void removePart(int id);
    public Part getPartById(int id);
    public List<Part> listParts();
    public int getMinBasePart();
    public void setOption(int opt);
    public int getOption();
    public void setSearchTitle(String searchTitle);
    public void setNoPage(int noPage);
    public int getNoPage();
    public int getTotalPages();
}
