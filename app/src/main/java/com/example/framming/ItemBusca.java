package com.example.framming;

public class ItemBusca {
    String idbusca;
    String imgbusca;
    String namebusca;
    String originalname;
    String anobusca;
    String overview;

    public ItemBusca(String idbusca, String imgbusca, String namebusca, String originalname, String anobusca, String overview) {
        this.idbusca = idbusca;
        this.imgbusca = imgbusca;
        this.namebusca = namebusca;
        this.originalname = originalname;
        this.anobusca = anobusca;
        this.overview = overview;
    }

    public String getIdbusca() {
        return idbusca;
    }

    public void setIdbusca(String idbusca) {
        this.idbusca = idbusca;
    }

    public String getImgbusca() {
        return imgbusca;
    }

    public void setImgbusca(String imgbusca) {
        this.imgbusca = imgbusca;
    }

    public String getNamebusca(){
        return namebusca;
    }
    public void setNamebusca(String namebusca){
        this.namebusca = namebusca;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getAnobusca() {
        return anobusca;
    }

    public void setAnobusca(String anobusca) {
        this.anobusca = anobusca;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
