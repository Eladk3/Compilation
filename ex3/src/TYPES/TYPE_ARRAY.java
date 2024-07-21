package TYPES;

public class TYPE_ARRAY extends TYPE{

    public TYPE typePointer;
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_ARRAY(TYPE type,String name)
	{
        this.typePointer = type;
		this.name = name;
	}
}
