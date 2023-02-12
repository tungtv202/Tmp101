package me.tungexplorer.study.reactor;

public class LoaderTMP {

    public static class Tung {
        private int temp;

        public Tung() {
            this.temp = 9;
        }
    }

    public static Tung module(){
        return new Tung();
    }


}
