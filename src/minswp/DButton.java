package minswp;
import javax.swing.JButton;

public class DButton extends JButton{
    boolean mine=false,flg=false;
    int num;
    MinSwp msp;
    
    public DButton(MinSwp ms)
    {
        num=-1;
        msp=ms;
    }
    
    void setMine(boolean b)
    {
        mine = b;
    }
    
    boolean isMine()
    {
        return mine;
    }
    
    void showMine()
    {
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minswp/m.jpg")));
    }

    void setFlaged(boolean en) {
        this.setEnabled(!en);
        if(en && !flg && msp.flg_num<msp.m){
            flg=true;
            msp.flg_num++;
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minswp/f.jpg")));}
        else{
            flg=false;
            msp.flg_num--;
            this.setIcon(new javax.swing.ImageIcon());
        }
        //if(msp.flg_num<=msp.m){
        msp.flaged_disp.setText(msp.flg_num+"");
    }
    
}