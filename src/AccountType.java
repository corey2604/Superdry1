public enum AccountType{
        Standard(500f,0),
        Saver(0f,1),
        Premium(3000f,0);
    private float overdraft;
    private float deductioncharge;

    private AccountType(float overdraft, float deductioncharge){
        this.overdraft = overdraft;
        this.deductioncharge = deductioncharge;
    }

         public float  getoverdraftlimit(){
             return overdraft;
         }

        public float getdeductioncharge(){
            return deductioncharge;
        }

}

