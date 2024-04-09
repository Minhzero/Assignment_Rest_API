package minhnqph38692.fpoly.assignment1.Interface;

import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;

public interface Item_ProductCart_Handle {
    public  void  Delete(String id);

    public  void UpdateT(String id , ProductDTOAPI productDTOAPI);
    public  void UpdateG(String id , ProductDTOAPI productDTOAPI);

}
