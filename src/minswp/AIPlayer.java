
package minswp;

/**
 *
 * Creator @ NKC
 * 
**/
public class AIPlayer{ //implements Runnable{

    Integer ai_array[][];
    int ai_f=0;
    boolean run=true;
    ButtonHandler[][] ai_bh;
    int ai_l=0,ai_w=0,ai_m=0;
    public AIPlayer(int l,int w,int m,ButtonHandler[][] bh) {
        ai_array=new Integer[l][w];
        ai_bh=bh;
        ai_l=l;
        ai_w=w;
        ai_m=m;
       // play(l,w,m,bh);
    }
    public void play()
    {
        while(run){
        //ai_bh=bh;   
        check(ai_l,ai_w);
        click0(ai_l,ai_w);
        //find1(ai_l,ai_w,ai_m,ai_bh);
        review(ai_l,ai_w,1);
        review(ai_l,ai_w,2);
        review(ai_l,ai_w,3);
        review(ai_l,ai_w,4);
        review(ai_l,ai_w,5);
        review(ai_l,ai_w,6);
        decide(ai_l,ai_w,ai_m,ai_bh);
        }
    }
    
    public void stop()
    {
        run=false;
    }
    
    public void pause()
    {
        
    }
    
    public void resume()
    {
        run=true;
    }
    public void setRunnable()
    {
        run=true;
    }

    int count_1=0,count_2=0,countv=0;//count1=0,count2=0,count3=0;
    
    private void check(int l,int w) {
       
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(ai_bh[i][j].db.isEnabled())
                {
                    ai_array[i][j]=-1;
                    count_1++;
                }
                else if(ai_bh[i][j].db.isVisible()){
                    ai_array[i][j]=10;
                    count_2++;
                }
                else{
                    ai_array[i][j]=ai_bh[i][j].NKC;
                    countv++;
                }
            }
        }
    }

    private void click0(int l,int w) {
         for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(ai_array[i][j]==0)
                {
                     for(int x=i-1;x<=i+1;x++)
                    {
                        for(int y=j-1;y<=j+1;y++)
                        {
                            try{
                                    if(ai_array[x][y]==-1)
                                    {
                                        clickMove(x,y);
                                    }
                                    else
                                    {               
                                    }
                                }
                            catch(Exception ex)
                            {}
                        }
                    }
                }
            }
        }
    }
    
    private void find1(int l,int w,int m,ButtonHandler[][] bh) {
         for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(ai_array[i][j]==-1)
                {
                    int count1=0;
                    for(int x=i-1;x<=i+1;x++)
                    {
                        for(int y=j-1;y<=j+1;y++)
                        {
                            try{
                                    if(ai_array[x][y]==1)
                                    {
                                        count1++;
                                    }
                                    else
                                    {
                                        
                                    }
                                }
                            catch(Exception ex)
                            {}
                        }
                    }
                    if(count1>=3 && ai_f++<20)
                    {
                        bh[i][j].ai_rightClick();
                        ai_f++;
                        System.out.print("\n nkc "+count1+" "+i+" "+j+" "+ai_f); 
                    }
                }
            }
        }
    }

    private void review(int l,int w,int n) {
        
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(ai_array[i][j]==n)
                {
                    int count_v=0;
                    int count_f=0;
                    for(int x=i-1;x<=i+1;x++)
                    {
                        for(int y=j-1;y<=j+1;y++)
                        {
                            try{
                                    if(ai_bh[x][y].db.isVisible())
                                    {
                                        count_v++;
                                        if(!ai_bh[x][y].db.isEnabled())
                                        {
                                            count_f++;
                                        }
                                    }
                                    else
                                    {
                                        
                                    }
                            }
                            catch(Exception ex)
                            {}
                        }
                    }
                    if(count_v == n && count_f<n)
                     {
                        for(int x=i-1;x<=i+1;x++)
                        {
                            for(int y=j-1;y<=j+1;y++)
                            {
                                try{
                                    if(ai_bh[x][y].db.isEnabled() && ai_f<20){
                                        ai_f++;
                                        ai_bh[x][y].ai_rightClick();
                                    }
                                }
                                catch(Exception ex){}      
                            }
                        }
                     }
                    else if(count_f==n && count_v > n) 
                    {
                        for(int x=i-1;x<=i+1;x++)
                        {
                            for(int y=j-1;y<=j+1;y++)
                            {
                                try{
                                    if(ai_bh[x][y].db.isEnabled()){
                                        clickMove(x,y);
                                    }
                                }
                                catch(Exception ex){}      
                            }
                        }
                    }                    
                    else{
                    }
                }
            }
        }
    }
    
    int nonstop=0;
    
    private void decide(int l,int w,int m,ButtonHandler[][] bh) {
        
        if(count_1==l*w)
        {
            randomMove(l,w);
        }
        else if(ai_f==m)
        {
            for(int x=0;x<10;x++)
            {
                for(int y=0;y<10;y++)
                {
                    if(bh[x][y].db.isEnabled())
                    {
                        clickMove(x,y);
                    }
                }
            }
            run=false;
        }
        //else if()
        else{
            //randomMove(l,w);      
            System.out.print("\n nkc else"); 
                if(nonstop<10 && nonstop>8 && ai_bh[0][0].db.isEnabled())
                {
                    ai_bh[0][0].db.doClick();
                }
                else if(nonstop<10 && nonstop>7 && ai_bh[l-1][0].db.isEnabled())
                {
                    ai_bh[l-1][0].db.doClick();
                }
                else if(nonstop<10 && nonstop>6){
                 //   findSafeMove();    
                }
                else if(nonstop<10 && nonstop>5 && ai_bh[l-1][w-1].db.isEnabled())
                {
                    ai_bh[l-1][w-1].db.doClick();
                }
                
                else if(nonstop<10 && nonstop>4 && ai_bh[0][w-1].db.isEnabled())
                {
                    ai_bh[0][w-1].db.doClick();
                }
                else if(nonstop<=25 && nonstop>=0){
                    delima();   
                }
                else{
                    delima();
                    System.out.print(" ^"+nonstop+"^ ");
                    run=false;
                }
            nonstop++;
        } 
    }
       

    private void clickMove(int x,int y) {
        ai_bh[x][y].db.doClick();
        System.out.print(" "+x+" "+y+" ");
    }
    
    private void randomMove(int l,int w) {
        
        int x = Math.abs(Math.round((float)(Math.random()*l))-1);
        int y = Math.abs(Math.round((float)(Math.random()*w))-1);
        if(ai_bh[x][y].isEnabled())
        {
            ai_bh[x][y].db.doClick();
            System.out.print(" "+x+" "+y+" ");
        }
        else
        {
            randomMove(l,w);
        }
    }

    void showTable(int l,int w) {
       System.out.print("\n");
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<w;j++)
            {
                System.out.print(ai_array[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

 //   @Override
   // public void run() {
     //   play();
    //}

    private void delima() {
        int XC=-12,YC=-12;
        int count1=22;
        ai_setFlag();
        for(int i=0;i<ai_l;i++)
        {
            for(int j=0;j<ai_w;j++)
            {
                if(ai_array[i][j]==-1)
                {
                    int count=0;
                    for(int x=i-1;x<=i+1;x++)
                    {
                        for(int y=j-1;y<=j+1;y++)
                        {
                            try{
                                count +=ai_array[x][y];
                            }
                            catch(Exception ex)
                            {}
                        }
                    }
                    if(count<count1)
                    {
                        System.out.print(" "+count+" ");
                        count1=count;
                        XC=i;YC=j;
                    }
                }
            }
        }
        System.out.print("\n nkc delima "+XC+" "+YC+" ");
        ai_bh[XC][YC].db.doClick();
    }

    private void findSafeMove() {
        for(int i=0;i<ai_l;i++)
        {
            for(int j=0;j<ai_w;j++)
            {
                if(ai_array[i][j]==-1)
                {
                    int count1=0;
                    for(int x=i-1;x<=i+1;x++)
                    {
                        for(int y=j-1;y<=j+1;y++)
                        {
                            try{
                                    if(ai_array[x][y]==-1 || ai_array[x][y]==0)
                                    {
                                        count1++;
                                    }
                                    else
                                    {
                                        
                                    }
                                }
                            catch(Exception ex)
                            {}
                        }
                    }
                    if(count1==9)
                    {
                        ai_bh[i][j].db.doClick();
                    }
                }
            }
        }
    }
    
    private void ai_setFlag() {
        
        for(int i=0;i<ai_l;i++)
        {
            for(int j=0;j<ai_w;j++)
            {
                if(ai_array[i][j]==-1)
                {
                    try
                    {/*
                        if(ai_array[i-1][j-1]>0 && ai_array[i-1][j]>0 && ai_array[i][j-1]>0)
                        {
                            ai_bh[i][j].ai_rightClick();
                        }
                        else if(ai_array[i+1][j+1]>0 && ai_array[i+1][j]>0 && ai_array[i][j+1]>0)
                        {
                            ai_bh[i][j].ai_rightClick();
                        }
                        else if(ai_array[i-1][j+1]>0 && ai_array[i-1][j]>0 && ai_array[i][j+1]>0)
                        {
                            ai_bh[i][j].ai_rightClick();
                        }
                        else if(ai_array[i+1][j-1]>0 && ai_array[i+1][j]>0 && ai_array[i][j-1]>0)
                        {
                            ai_bh[i][j].ai_rightClick();
                        }*/
                    }
                    catch(Exception ex)
                    {}
                }
            }
        }
    }
}
