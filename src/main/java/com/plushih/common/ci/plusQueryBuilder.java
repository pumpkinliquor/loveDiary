package com.plushih.common.ci;

import com.google.gson.Gson;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.FieldInfoEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 
 * 쿼리빌더
 * 코드이그나이터처럼 사용하기위함
 */
public class plusQueryBuilder {

	public LinkedHashMap<String, String> _join = new LinkedHashMap<String, String>();
	public Map<String, String> _where = new HashMap<String, String>();
	public Map<String, String> _whereOne = new HashMap<String, String>();
	public Map<String, String> _where_not = new HashMap<String, String>();
	public Map<String, String[]> _where_not_in = new HashMap<String, String[]>();
	public Map<String, String> _or = new HashMap<String, String>();
	public Map<String, String> _blockor = new HashMap<String, String>();
	public Map<String, String[]> _where_in = new HashMap<String, String[]>();
	public Map<String, String> _like = new HashMap<String, String>();
	public Map<String, String> _lt = new HashMap<String, String>();
	public Map<String, String> _lteq = new HashMap<String, String>();
	public Map<String, String> _gtlt = new HashMap<String, String>();
	public Map<String, String> _gt = new HashMap<String, String>();
	public Map<String, String> _gteq = new HashMap<String, String>();
	public Map<String, String> _or_like = new HashMap<String, String>();
	public LinkedHashMap<String,String> _values = new LinkedHashMap<String,String>();

	public LinkedHashMap<String,String> _historyvalues = new LinkedHashMap<String,String>();

	public LinkedHashMap<String,FieldInfoEntity> _fieldList = new LinkedHashMap<String, FieldInfoEntity>();


	public String // Active record
			_db_prefix = "", _id="",pageName="", flag="",
			_select = "*", _group_by = "", _having = "", _order_by = "", _limit = "",batchSql="",querySql="",_join_and="";
    public HttpServletRequest request;
    public Input input;
    public String umId;
    public String umSeq;
    public Integer insert_id = 0;
    public Integer draw= 0;
	public String _table,_table_sub;
	public String _primary;
	public String errMsg = "";

	public String _USERID;
	public String _USERSEQ;
	public String _LOCATION;

	public boolean requireState =false;
	public boolean _DEBUG =false;
	public boolean _CommnetDEBUG =false;
	public boolean _QueryDEBUG =false;
	public boolean _QueryValue =true;

	public int minChar =0;
	public int maxChar =0;
	public List<HashMap<String,String>> batchItem =null;
	public HashMap<String,String> batchAdd =null;

	public void checkInsertUpdate() throws Exception {
		if(flag.equals(queryType.DUP) || flag.equals(queryType.ERR)) {
			throw new Exception(errMsg);
		}
	}

	public final class checkLast  {
		public static final String getList  = "getList";
		public static final String getRow = "getRow";
		public static final String INSERT = "INSERT";
		public static final String UPDATE= "UPDATE";
	}
	public final class inType  {
		public static final String INSERT  = "INSERT";
		public static final String UPDATE = "UPDATE";
		public static final String DELETE = "DELETE";
		public static final String DUP = "DUP";
		public static final String UNI = "UNI";
		public static final String ERR = "ERR";
		public static final String SESS = "SESS";
		public static final String TEMP = "TEMP";
		public static final String PARAM = "PARAM";
	}
	public final class joinType  {
		public static final String INNER  = "INNER";
		public static final String RIGHT = "RIGHT";
		public static final String LEFT = "LEFT";
	}
	public final class queryType  {
		public static final String INSERT  = "INSERT";
		public static final String BATCH  = "BATCH";
		public static final String SELECT = "SELECT";
		public static final String SELECT_SEB_QUERY = "SELECT_SEB_QUERY";
		public static final String COUNT = "COUNT";
		public static final String UPDATE = "UPDATE";
		public static final String DELETE = "DELETE";
		public static final String TABLE = "TABLE";
		public static final String ERR = "ERR"; //Err Seq값과 Unique 조회값이 틀린경우
		public static final String DUP = "DUP"; //이미 존재하는 Unique
	}



	public plusQueryBuilder clearQuery() {
		_table ="";
		_table_sub ="";
		_where_in.clear();
		_join.clear();
		_join_and = "";
		_where.clear();
		_whereOne.clear();
		_where_not.clear();
		_like.clear();
		_lt.clear();
		_gt.clear();
		_lteq.clear();
		_gteq.clear();
		_or_like.clear();
		_select = "*";
		_group_by = "";
		_order_by = "";
		_values.clear();

		return this;
	}
	public plusQueryBuilder clearWhere() {
		//_table ="";
		_where_in.clear();
		_where_not_in.clear();
		_whereOne.clear();
		_join.clear();
		_join_and = "";
		_lteq.clear();
		_gteq.clear();
		_lt.clear();
		_gt.clear();
		_where.clear();

		_whereOne.clear();
		_where_not.clear();
		_like.clear();
		_or_like.clear();

		return this;
	}
	public void checkBefore(String queryType) throws Exception {
		if(StringUtils.isEmpty(this._table)){
			throw new Exception();
		}
		switch (queryType){
			default:
			case plusQueryBuilder.queryType.SELECT:
				selectQuery(false);
				break;
				case plusQueryBuilder.queryType.SELECT_SEB_QUERY:
				selectSubQuery();
				break;
			case plusQueryBuilder.queryType.COUNT:
				selectQuery(true);
				break;

			case plusQueryBuilder.queryType.TABLE:
			break;
			case plusQueryBuilder.queryType.INSERT:
				insertQuery();
			break;
			case plusQueryBuilder.queryType.UPDATE:
				updateQuery();
			break;
			case plusQueryBuilder.queryType.DELETE:
				deleteQuery();
			break;
			case plusQueryBuilder.queryType.BATCH:
				batchSql = batchQuery();
			break;

		}
	}
	public String selectSubQuery() {
        Iterator it;
        String SQL ="";
        SQL +="\r\n" + "SELECT " + this._select + " \r\nFROM " + this._table_sub;
        Debug.log(SQL,"########## Mapper ##########\r\n<select id=\""+_id+"\" parameterType=\"dbEntity\"  resultType=\"camelMap\">\r\n/* Query In"+ _id +"*/","</select>");
        return SQL;
    }
	public void checkBefore(String queryType,boolean pk) throws Exception {

		if(pk==true){
			if(_where.size()==0){
				throw new Exception();
			}
		}
		checkBefore(queryType);
	}
	/**
	 * Escape SQL parameters, removing ' " # -- ; `
	 * @param value
	 * @return String
	 */
	protected static String escape(String value) {
		// TODO: clean
		return value;
	}
	//주석을 입력하게한다
	public String queryComment(String fieldName){
		String returnCommnt = "";

		if(_QueryDEBUG==true){

			if(_fieldList.containsKey(fieldName)){
				returnCommnt+=" /* ";
				returnCommnt+=" [";
				returnCommnt+=_fieldList.get(fieldName).getColumnName();
				returnCommnt+=":"+_fieldList.get(fieldName).getColumnComment();
				returnCommnt+=":"+_fieldList.get(fieldName).getColumnType();
				returnCommnt+=":"+_fieldList.get(fieldName).getDataLength();
				returnCommnt+="] ";
				returnCommnt+=" */ ";
			}
		}
		return returnCommnt;
	}
	
	public String whereQuery(){
		Iterator it;
		String SQL = "";
		SQL += "\r\n" + "WHERE 1=1";

		// WHERE
		if (_where.size() > 0) {
			it = _where.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ("AND " ) + map.getKey() + " = " + map.getValue() + queryComment((String)map.getKey());
			}
		}
		// WHERE
		if (_where_not.size() > 0) {
			it = _where_not.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ("AND " ) + map.getKey() + " <> " + map.getValue() + queryComment((String)map.getKey());
			}
		}

		// WHERE
		if (_where_in.size() > 0) {

			it = _where_in.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				StringBuilder sb = new StringBuilder();

				for (String str : _where_in.get(map.getKey())) {
					sb.append("'" + str + "',");
				}

				sb.setLength(sb.length() - 1);

				SQL += "\r\n" + ("AND " ) + map.getKey() + " IN("
						+ sb.toString() + ")" + queryComment((String)map.getKey());
			}
		}
		// WHERE
		if (_where_not_in.size() > 0) {

			it = _where_not_in.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				StringBuilder sb = new StringBuilder();

				for (String str : _where_not_in.get(map.getKey())) {
					sb.append("'" + str + "',");
				}

				sb.setLength(sb.length() - 1);

				SQL += "\r\n" + ("AND " ) + map.getKey() + " NOT IN("
						+ sb.toString() + ")" + queryComment((String)map.getKey());
			}
		}

		// LIKE
		if (_like.size() > 0) {
			it = _like.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ("AND " ) + map.getKey() + " LIKE('"
						+ map.getValue() + "')" + queryComment((String)map.getKey());
			}
		}

		// OR LIKE
		if (_or_like.size() > 0) {

			it = _or_like.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( "OR " ) + map.getKey() + " LIKE('"
						+ map.getValue() + "')" + queryComment((String)map.getKey());
			}
		}

		// LT <
		if (_lt.size() > 0) {
			it = _lt.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( "AND " ) + map.getKey() + " <'"
						+ map.getValue() + "'" + queryComment((String)map.getKey());
			}
		}
		if (_lteq.size() > 0) {
			it = _lteq.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( "AND " ) + map.getKey() + " <= '"
						+ map.getValue() + "'" + queryComment((String)map.getKey());
			}
		}
		if (_gtlt.size() > 0) {
			it = _gtlt.entrySet().iterator();
			SQL += "\r\n" + ( "AND ( " );
			int vx=0;
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( vx>0 ? ( "AND " ) :"") + map.getKey() + " <= '"
						+ map.getValue() + "'" + queryComment((String)map.getKey());
				vx++;
			}
			SQL += "\r\n" + ( ") " );
		}
		// GT <
		if (_gt.size() > 0) {
			it = _gt.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( "AND " ) + map.getKey() + " >'"
						+ map.getValue() + "'" + queryComment((String)map.getKey());
			}
		}
		if (_gteq.size() > 0) {

			it = _gteq.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				SQL += "\r\n" + ( "AND " ) + map.getKey() + " >='"
						+ map.getValue() + "'" + queryComment((String)map.getKey());
			}
		}


		return SQL;
	}
	public Object getAcives(){

		HashMap<String,Object>  map = new HashMap<String,Object>();
		map.put("_table",_table);
		map.put("_join",_join);
		map.put("_where",_where);
		map.put("_where_not",_where_not);
		map.put("_like",_like);
		map.put("_where_in",_where_in);
		map.put("_where_not_in",_where_not_in);
		map.put("_or",_or);
		map.put("_lt",_lt);
		map.put("_lteq",_lteq);
		map.put("_gt",_gt);
		map.put("_gteq",_gteq);
		map.put("_or_like",_or_like);
		map.put("_values",_values);
		map.put("_order_by",_order_by);
		map.put("_group_by",_group_by);
		return map;
	}

	/**
	 * selectQuery
	 * @return SQL
	 */
	public String selectQuery(Boolean countCheck) {
		Cache cc= new Cache();
		cc.set_cache("SELECT."+this._table.replaceAll("\\p{Z}", "_as_"),(new Gson()).toJson(getAcives()));
		Iterator it;
		String SQL ="";
		//SQL +="\r\n########### Start selectQuery ##########";
		SQL +="\r\n" + "SELECT " + this._select + " \r\nFROM " + this._table;

		// JOIN
		if (_join.size() > 0) {
			it = _join.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();
				// Get table and type of join... e.g: table|left
				//String[] joinType = map.getKey().toString().split("\\|");
				SQL += "\r\n" + map.getKey().toString()
						+ " ON ( " + map.getValue() +" )";
			}

		}


		SQL += whereQuery();

		// GROUP BY
		if (_group_by.length() > 0) {
			SQL += "\r\n" + "GROUP BY " + _group_by;
		}
		// ORDER BY
		if (_order_by.length() > 0) {
			SQL += "\r\n" + "ORDER BY " + _order_by;
		}

		if(!countCheck){
			// LIMIT
			if(_limit!=null){
				if (_limit.length() > 0) {
					SQL += "\r\n" + "LIMIT " + _limit;
				}
			}
		}
		//SQL +="\r\n########### End selectQuery ##########";
		//Debug.log(SQL,"########## Start selectQuery ##########\r\n\r\n/* Query In "+ _id +"*/","########## End selectQuery ##########");
		
		Debug.log(SQL,"########## Mapper ##########\r\n<select id=\""+_id+"\" parameterType=\"dbEntity\"  resultType=\"camelMap\">\r\n/* Query In"+ _id +"*/","</select>");
		return SQL;
	}
	/**
	 * selectQuery
	 * @return SQL
	 */
	public String insertQuery() {
		Cache cc= new Cache();
		cc.set_cache("INSERT."+this._table.replaceAll("\\p{Z}", "_as_"),(new Gson()).toJson(getAcives()));
		String SQL ="";
		//SQL +="\r\n########### Start insertQuery ##########";
		SQL +="\r\n/*"+ _id +"*/";
		SQL += "\r\nINSERT INTO "+ this._table+ "(\r\n%s\r\n) VALUES (\r\n%s\r\n)";
		
		Iterator it = this._values.entrySet().iterator();
		String columns = "", values = "";
		while (it.hasNext()) {
			Map.Entry map = (Map.Entry) it.next();
			if(map.getValue()!=null){

				columns += "," + map.getKey() + "\r\n";
				if(_QueryValue){
					values +=  ((map.getValue()== null ? "NULL" : plusQueryBuilder.escape(map.getValue().toString()) )) +  queryComment((String)map.getKey()) ;
				} else {

					values += ", "+ "#{"+StringUtils.toCamel(map.getKey().toString())+"} " +  queryComment((String)map.getKey()) ;
				}
				//+ (map.getValue()== null ? "NULL" : "" + plusQueryBuilder.escape(map.getValue().toString()) + "")+ "*/\r\n";
			}
		}
		//SQL = String.format(SQL, columns.substring(0, columns.length()-1),values.substring(0, values.length()-1));
		SQL = String.format(SQL, columns.substring(1),values.substring(1));
		
		//SQL +="\r\n########### End insertQuery ##########";
		//Debug.log(SQL,"########## Start insertQuery ##########\r\n\r\n/* Query In "+ _id +"*/","########## End insertQuery ##########");

		Debug.log(SQL,"<insert id=\""+_id+"\" parameterType=\"dbEntity\">\r\n/* Query In"+ _id +"*/","</insert>");
		return SQL;

	}

	/**
	 * updateQuery
	 * @return SQL
	 */
	public String updateQuery() {
		Cache cc= new Cache();
		cc.set_cache("UPDATE."+this._table.replaceAll("\\p{Z}", "_as_"),(new Gson()).toJson(getAcives()));
		String SQL ="";
		//SQL +="\r\n########### Start updateQuery ##########";
		SQL +="\r\n/*"+ _id +"*/";
		SQL += "\r\nUPDATE "+ this._table+ " SET \r\n%s ";

		Iterator it = this._values.entrySet().iterator();
		String columns = "", values = "";
		while (it.hasNext()) {
			Map.Entry map = (Map.Entry) it.next();
			if(_QueryValue){
				columns += "," + map.getKey() + " = "+ ((map.getValue()== null ? "NULL" : plusQueryBuilder.escape(map.getValue().toString()) )) +  queryComment((String)map.getKey()) +"\r\n";
			} else {

				columns += "," + map.getKey() + " = "+ "#{"+StringUtils.toCamel(map.getKey().toString())+"} " +  queryComment((String)map.getKey()) +"\r\n";
			}
			//+"/*"++"*/\r\n";
			//values += ;
		}
		//SQL = String.format(SQL, columns.substring(0, columns.length()-1));
		SQL = String.format(SQL, columns.substring(1));

		SQL += "\r\n" + whereQuery();
		//SQL +="\r\n########### End updateQuery ##########";
		//Debug.log(SQL,"########## Start updateQuery ##########\r\n\r\n/* Query In "+ _id +"*/","########## End updateQuery ##########");

		Debug.log(SQL,"<update id=\""+_id+"\" parameterType=\"dbEntity\">\r\n/* Query In"+ _id +"*/","</update>");
		return SQL;

	}

	/**
	 * batchQuery
	 * @return SQL
	 */
	public String batchQuery() {
		String SQL ="";
		//SQL +="\r\n########### Start insertQuery ##########";
		SQL +="\r\n/* batch  "+ _id +"*/";
		SQL += "\r\nINSERT INTO "+ this._table+ "(\r\n%s\r\n) VALUES ";

		List<String> arrs = new ArrayList<String>();
		List<String> batchArrs = new ArrayList<String>();
		Iterator it = this._values.entrySet().iterator();
		String columns = "", values = "";

		String addAql = "";
		if(batchItem.size()>0){

			while (it.hasNext()) {
				Map.Entry map = (Map.Entry) it.next();

				String camelKey = StringUtils.toCamel(map.getKey().toString());
				if(batchItem.get(0).containsKey(camelKey)  ) {
					columns += "," + map.getKey() ;
					arrs.add(map.getKey().toString());

					addAql+=","+map.getKey() + " = values("+map.getKey()+")\r\n";
				}
				if(batchAdd!=null){
					if(batchAdd.containsKey(map.getKey().toString())  ) {

						columns += "," + map.getKey();
						arrs.add(map.getKey().toString());

						addAql += "," + map.getKey() + " = values(" + map.getKey() + ")\r\n";
					}
				}


			}
			if(!StringUtils.isEmpty(columns)){
				SQL = String.format(SQL, columns.substring(1));
			}

			int i=0;
			for(HashMap<String,String> row : batchItem) {
				//Debug.log( "::row=="+row);
				if(i>0) SQL +=",";
				String itemRow = "";

				if(arrs.size()>0){

					for(String batchKey : arrs){
						String camelKey = StringUtils.toCamel(batchKey);

						if(row.containsKey(camelKey)) {
							//Debug.log("batchKey==="+batchKey+"::"+row.get(camelKey));
							itemRow += ",'"+row.get(camelKey)+"'";

						}
						if(batchAdd!=null){
							if(batchAdd.containsKey(batchKey)){
								itemRow += ",'"+batchAdd.get(batchKey)+"'";
							}
						}

					}
				}
				if(itemRow.length()>0){

					SQL +=  "("+itemRow.substring(1)+")\r\n";
					i++;
				}
			}
		}

		SQL +="\r\rON DUPLICATE KEY UPDATE\r\n";

		Debug.log(addAql);
		if(addAql.length()>0){
			SQL += addAql.substring(1);
		}


		//Debug.log(SQL,"########## Start batchQuery ##########\r\n\r\n/* Query In "+ _id +"*/","########## End batchQuery ##########");

		return SQL;
	}
	/**
	 * deleteQuery
	 * @return SQL
	 */
	public String deleteQuery() {
		Cache cc= new Cache();
		cc.set_cache("DELETE."+this._table.replaceAll("\\p{Z}", "_as_"),(new Gson()).toJson(getAcives()));
		String SQL ="";
		//SQL +="\r\n########### Start deleteQuery ##########";
		SQL +="\r\n/*"+ _id +"*/";
		SQL += "\r\nDELETE FROM "+ this._table+ " ";

//		Iterator it = this._where.entrySet().iterator();
//		String columns = "", values = "";
//		while (it.hasNext()) {
//			Map.Entry map = (Map.Entry) it.next();
//			columns += ", " + map.getKey() + "";
//			values += ", " + map.getValue()== null ? "NULL" : "'" + plusQueryBuilder.escape(map.getValue().toString()) + "'";
//		}
		//SQL = String.format(SQL, columns.substring(0, columns.length()-1),values.substring(0, values.length()-1));
//		SQL = String.format(SQL, columns.substring(1),values.substring(1));

		SQL += "\r\n" + whereQuery();
		//Debug.log(SQL,"########## Start deleteQuery ##########\r\n\r\n/* Query In "+ _id +"*/","########## End deleteQuery ##########");

		Debug.log(SQL,"<delete id=\""+_id+"\" parameterType=\"dbEntity\">\r\n/* Query In"+ _id +"*/","</delete>");
		return SQL;

	}

}
