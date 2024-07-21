package TYPES;

public abstract class TYPE
{
	/******************************/
	/*  Every type has a name ... */
	/******************************/
	public String name;

	/*************/
	/* isClass() */
	/*************/
	public boolean isClass(){ return this instanceof TYPE_CLASS;}

	/*************/
	/* isArray() */
	/*************/
	public boolean isArray(){ return this instanceof TYPE_ARRAY;}

	public boolean equals(TYPE obj) {
		// TODO Auto-generated method stub
		return this.name.equals(obj.name);
	}

    public boolean isEqualsOrSubTypeOf(TYPE t2) {

		boolean b1 = this.equals(t2);
		boolean b2 = TYPE_NIL.canAssignNil(this) && t2 instanceof TYPE_NIL;
		boolean b3 = (this instanceof TYPE_CLASS && t2 instanceof TYPE_CLASS &&
		((TYPE_CLASS) t2).isAncestorOf((TYPE_CLASS) this));

		return b1 || b2 || b3;
				
    }

	public TYPE getTypeOrTypeClassVarDec(){
		if (this instanceof TYPE_CLASS_VAR_DEC){
			return ((TYPE_CLASS_VAR_DEC)this).t;
		}
		else if (this instanceof TYPE_CLASS){
			return this;
		}
		return null;
	}
}
