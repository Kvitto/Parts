package my.internship.parts.dao;

import my.internship.parts.model.Part;

import java.util.List;

public interface PartDao {
    public void addPart(Part part);
    public void updatePart(Part part);
    public void removePart(int id);
    public Part getPartById(int id);
    public List<Part> listParts();
    public List<Part> listParts(String search);
    public List<Part> listSort(int opt);
    public int getMinBasePart();

}
