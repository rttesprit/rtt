package edu.fundup.model.iservice;



import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.entity.TypeReclamation;

import java.util.ArrayList;

public interface ITypeReclamation {
    ArrayList<TypeReclamation> getTypeReclmationList();
    TypeReclamation getTypeReclmation(int id);
    void addType(TypeReclamation tr);
    void updateType(TypeReclamation tr);
    void deleteType(TypeReclamation tr);
    ArrayList<TypeReclamation> getTypeReclamationListWithType(String type);
    Boolean TypeReclamationExist(TypeReclamation typeReclamation);

}
