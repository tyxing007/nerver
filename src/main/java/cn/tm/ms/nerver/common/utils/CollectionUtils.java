package cn.tm.ms.nerver.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 集合工具类<br>
 * 排序：若int/float/double的数值被作为String传人排序，则会按照String进行排序，而不会按照数值进行排序
 * @author: lry
 * @date: 2015年9月12日 下午1:02:04
 * @version: v0.0.1
 */
public class CollectionUtils {

	/**
	 * 排序方式<br>
	 * DESC为降序<br>
	 * ASC为升序<br>
	 */
	public enum SORT {
		DESC, ASC
	}

	/**
	 * 对list中的元素按升序排列.
	 * @param <T>
	 * 
	 * @param list 排序集合
	 * @param field 排序字段
	 * @return
	 */
	public static <T> List<T> sort(List<T> list, final String field) {
		return sort(list, field, SORT.ASC);
	}

	/**
	 * 对list中的元素进行排序.
	 * 
	 * @param list 排序集合
	 * @param field 排序字段
	 * @param sort 排序方式
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> sort(List<T> list, final String field, final SORT sort) {
		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Field f = a.getClass().getDeclaredField(field);
					f.setAccessible(true);
					Class<?> type = f.getType();
					if (type == int.class) {
						ret = ((Integer) f.getInt(a)).compareTo((Integer) f.getInt(b));
					} else if (type == double.class) {
						ret = ((Double) f.getDouble(a)).compareTo((Double) f.getDouble(b));
					} else if (type == long.class) {
						ret = ((Long) f.getLong(a)).compareTo((Long) f.getLong(b));
					} else if (type == float.class) {
						ret = ((Float) f.getFloat(a)).compareTo((Float) f.getFloat(b));
					} else if (type == Date.class) {
						ret = ((Date) f.get(a)).compareTo((Date) f.get(b));
					} else if (isImplementsOf(type, Comparable.class)) {
						ret = ((Comparable) f.get(a)).compareTo((Comparable) f.get(b));
					} else {
						ret = String.valueOf(f.get(a)).compareTo(String.valueOf(f.get(b)));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return sort.equals(SORT.DESC) ? (-ret) : ret;
			}
		});
		return list;
	}

	/**
	 * 对list中的元素按fields和sorts进行排序,
	 * fields[i]指定排序字段,sorts[i]指定排序方式.如果sorts[i]为空则默认按升序排列.
	 * @param <T>
	 * 
	 * @param list
	 * @param fields
	 * @param sorts
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> sort(List<T> list, String[] fields, SORT[] sorts) {
		if (fields != null && fields.length > 0) {
			for (int i = fields.length - 1; i >= 0; i--) {
				final String field = fields[i];
				SORT tmpSort = SORT.ASC;
				if (sorts != null && sorts.length > i && sorts[i] != null) {
					tmpSort = sorts[i];
				}
				final SORT sort = tmpSort;
				Collections.sort(list, new Comparator() {
					public int compare(Object a, Object b) {
						int ret = 0;
						try {
							Field f = a.getClass().getDeclaredField(field);
							f.setAccessible(true);
							Class<?> type = f.getType();
							if (type == int.class) {
								ret = ((Integer) f.getInt(a)).compareTo((Integer) f.getInt(b));
							} else if (type == double.class) {
								ret = ((Double) f.getDouble(a)).compareTo((Double) f.getDouble(b));
							} else if (type == long.class) {
								ret = ((Long) f.getLong(a)).compareTo((Long) f.getLong(b));
							} else if (type == float.class) {
								ret = ((Float) f.getFloat(a)).compareTo((Float) f.getFloat(b));
							} else if (type == Date.class) {
								ret = ((Date) f.get(a)).compareTo((Date) f.get(b));
							} else if (isImplementsOf(type, Comparable.class)) {
								ret = ((Comparable) f.get(a)).compareTo((Comparable) f.get(b));
							} else {
								ret = String.valueOf(f.get(a)).compareTo(String.valueOf(f.get(b)));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return sort.equals(SORT.DESC) ? (-ret) : ret;
					}
				});
			}
		}
		return list;
	}

	/**
	 * 判断对象实现的所有接口中是否包含szInterface
	 * 
	 * @param clazz
	 * @param szInterface
	 * @return
	 */
	public static boolean isImplementsOf(Class<?> clazz, Class<?> szInterface) {
		boolean flag = false;
		Class<?>[] face = clazz.getInterfaces();
		for (Class<?> c : face) {
			if (c == szInterface) {
				flag = true;
			} else {
				flag = isImplementsOf(c, szInterface);
			}
		}
		if (!flag && null != clazz.getSuperclass()) {
			return isImplementsOf(clazz.getSuperclass(), szInterface);
		}
		return flag;
	}

	/**
	 * 根据sortField字段进行排序，然后根据groupbyField字段进行分组<br>
	 * 排序标准为每一组中最大或最小值进行对比<br>
	 * 通过get方法进行对比
	 * @param sortField 排序字段
	 * @param sort 排序方式，ASC为升序，DESC为降序
	 * @param groupbyField 分组字段
	 * @param list 带处理集合
	 * @return key-list
	 */
	public static <T> LinkedHashMap<Object, List<T>> sortGroupBy(String sortField,SORT sort,String groupbyField,List<T> list) {
		sort(list, sortField, sort);
		return groupBy(groupbyField, list);
	}
	
	/**
	 * 根据key字段将List中的实体进行分组<br>
	 * 通过get方法进行对比
	 * @param groupbyField 分组字段
	 * @param list 带处理集合
	 * @return key-list
	 */
	public static <T> LinkedHashMap<Object, List<T>> groupBy(String groupbyField,List<T> list) {
		int strLen;
        if (groupbyField == null || (strLen = groupbyField.length()) == 0 || list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(groupbyField.charAt(i)) == true) {
                return null;
            }
        }
        List<T> temp=null;
		//key-values
		LinkedHashMap<Object, List<T>> linkedhashmap=new LinkedHashMap<Object, List<T>>();
		//组装get方法
		groupbyField="get"+groupbyField.substring(0,1).toUpperCase()+groupbyField.substring(1);
		try {
			for (T t:list) {
				Object obj=t.getClass().getMethod(groupbyField).invoke(t,new Object[0]);//getKey()
				temp=linkedhashmap.get(obj);//get
				if(temp==null){
					temp=new ArrayList<T>();
				}
				temp.add(t);//add
				linkedhashmap.put(obj, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linkedhashmap;
	}
}