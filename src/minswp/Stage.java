
package minswp;

import java.awt.Color;

/**
 *
 * @author NKC
 * 
 */
public class Stage extends javax.swing.JPanel {
    /**
     * Creates new form Stage
     */
    int LE,WE;
    Integer[] mine_array;
    Integer[][] mine_arr;
    Integer []mine_arr_2;
    int NM=0;
    ButtonHandler[][] bh2;
    public Stage(int l,int w,int m,MinSwp msp) {
        initComponents();
        setLayout(new java.awt.GridLayout(l, w, 1, 1));
        newStage(l,w,m,msp);
    }
    public void newStage(int l,int w,int m,MinSwp msp)
    {
        LE=l;WE=w;
        NM=m;
        try{
            this.removeAll();
            setLayout(new java.awt.GridLayout(l, w, 1, 1));
        }
        catch(Exception ex)
        {
            setLayout(new java.awt.GridLayout(l, w, 1, 1));
        }
        ButtonHandler[][] bh = new ButtonHandler[l][w];
        bh2=bh;
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                bh[i][j]=new ButtonHandler(i,j,this,msp);
                this.add(bh[i][j]);
            }
        }
        addMine(l,w,m);
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                bh[i][j].addNum(checkNeibourMine(i,j,bh));
            }
        }
    }

    void addMine(int l,int w,int m)
    {
        Integer []mine_arr1 = new Integer[m];
        Integer []mine_arr2 = new Integer[m];
        for(int i=0;i<m;i++)
        {
            int k = Math.abs(Math.round((float)(Math.random()*l))-1);
            int p = Math.abs(Math.round((float)(Math.random()*w))-1);
            
            for(int j=0;j<i;j++)
            {
                
                if(mine_arr1[j]==k && mine_arr2[j]==p)
                {
                    i--;
                    k=-1;
                    break;
                }
            }
            
            if(k!=-1)
            {
                //System.out.print(k+" "+p+" *");
                mine_arr1[i]=k;
                mine_arr2[i] = p;
                bh2[k][p].setMine(true);
                bh2[k][p].addNum(10);    
               // System.out.print((mine_array[i])+" "+(mine_arr2[i]));
            }
        }
        
     mine_arr_2=mine_arr2;
     mine_array = mine_arr1; 
    }
    
    private int checkNeibourMine(int i,int j,ButtonHandler[][] bh)
    {
        int count=0;
        
        for(int q=i-1;q<=i+1;q++)
        {
           for(int w=j-1;w<=j+1;w++)
           {
               try{
                    if(bh[q][w].db.isMine())
                    {
                        count++;
                    }
                    else
                    {               
                    }
                  }
               catch(Exception ex)
               {}
           }
        }
        return count;
    }
    
   boolean dir=false;
   
    void clear(int i,int j)
    {
        if(dir)
        {
            gO(i-1,j);
            gO(i,j+1);
            
        }
        else
        {
            gO(i,j-1);
            gO(i+1,j);
        }    
    }
    public void gO(int q,int w)
    {
         try{
                    switch(bh2[q][w].getNum())
                    {
                        case 0:
                            bh2[q][w].db.doClick();
                            dir=!dir;
                            clear(q,w);
                            break;
                        case 1:
                            bh2[q][w].db.doClick();
                            break;
                        case 2:
                            bh2[q][w].db.doClick();
                            dir=!dir;
                            break;
                        case 3:
                            bh2[q][w].db.doClick();
                            break;
                        default:       
                    }
                   
                  }
               catch(Exception ex)
               {}
    }
    public void setDisable(int l,int w)
    {
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                bh2[i][j].db.setEnabled(false);
            }
        }
    }
    public void discloseMine()
    {
        for(int i=0;i<NM;i++)
        {
            bh2[(mine_array[i])][(mine_arr_2[i])].db.showMine();
           // bh2[(mine_array[i])][(mine_arr_2[i])].db.setEnabled(true);
        }
    }
    
    public void getCopyMine(int l,int w)
    {
        for(int i=0;i<l;i++){
            for(int j=0;j<w;j++){
                bh2[i][j].removeLabel();
                bh2[i][j].db.setVisible(true);
                bh2[i][j].db.setEnabled(true);
                bh2[i][j].db.setFlaged(false);
                //bh2[i][j].db.setBackground(Color.WHITE);
               // bh2[i][j].setMine(false);
                //bh2[i][j].addNum(-1);
            }    
        }
        /*for(int i=0;i<NM;i++)
        {   
                int k = mine_array[i];
                int p = mine_arr_2[i];
                bh2[k][p].setMine(true);
                bh2[k][p].addNum(10);    
        }*/
         for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                bh2[i][j].addNum(checkNeibourMine(i,j,bh2));
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
/* 
    if(bh[q][w].getNum()==0)
                    {
                        bh[q][w].db.doClick();
                        clear(q,w);
                    }
                    else if(bh[q][w].getNum()==1)
                    {
                        bh[q][w].db.doClick();
                    }
                    else if(bh[q][w].getNum()==2)
                    {
                        bh[q][w].db.doClick();
                    }
                    else if(bh[q][w].getNum()==3)
                    {
                        bh[q][w].db.doClick();
                    }
                    else
                    {
                        
                    }
    
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
