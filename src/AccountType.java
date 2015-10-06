public enum AccountType{
        Standard(500f),
        Saver(0f),
        Premium(3000f);
    private float overdraft;

    private AccountType(float overdraft){
        this.overdraft = overdraft;
    }
         public float  getoverdraftlimit(){
             return overdraft;
         }

}

