/*     */ package dunn.doe2;
/*     */ 
/*     */ import booker.building_data.AlphaValue;
/*     */ import booker.building_data.BookerField;
/*     */ import booker.building_data.BookerObject;
/*     */ import booker.building_data.FieldValue;
/*     */ import booker.building_data.ListValue;
/*     */ import booker.building_data.ObjectValue;
/*     */ import booker.building_data.RealValue;
/*     */ import dunn.output.CodewordWriter;
/*     */ import dunn.output.FieldWriter;
/*     */ import dunn.output.KeywordExpressionWriter;
/*     */ import dunn.output.ListValueWriter;
/*     */ import dunn.output.NumericWriter;
/*     */ import dunn.output.ObjectValueWriter;
/*     */ import dunn.output.ObjectWriter;
/*     */ import dunn.output.StringValueWriter;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOE2Factory
/*     */ {
/*     */   public static BookerObject createObject(String name, String type) {
/*  41 */     BookerObject object = new BookerObject(type, name);
/*  42 */     object.setWriter(new ObjectWriter(object));
/*  43 */     return object;
/*     */   }
/*     */   
/*     */   public static BookerObject createObject(String type) {
/*  47 */     BookerObject object = new BookerObject(type, type);
/*  48 */     object.setWriter(new ObjectWriter(object));
/*  49 */     return object;
/*     */   }
/*     */   
/*     */   public static BookerObject createDefault(String type) {
/*  53 */     BookerObject object = createObject("SET-DEFAULT");
/*  54 */     object.addField(createCodewordField("FOR", type));
/*  55 */     return object;
/*     */   }
/*     */   
/*     */   public static BookerField createField(String name, FieldValue value) {
/*  59 */     BookerField field = new BookerField(name, value);
/*  60 */     field.setWriter(new FieldWriter(field));
/*  61 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createNumericField(String name, double value) {
/*  65 */     BookerField field = new BookerField(name, createNumericValue(value));
/*  66 */     field.setWriter(new FieldWriter(field));
/*  67 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createStringField(String name, String value) {
/*  71 */     BookerField field = new BookerField(name, createStringValue(value));
/*  72 */     field.setWriter(new FieldWriter(field));
/*  73 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createExpressionField(String name, String value) {
/*  77 */     BookerField field = new BookerField(name, createExpressionValue(value));
/*  78 */     field.setWriter(new FieldWriter(field));
/*  79 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createCodewordField(String name, String value) {
/*  83 */     BookerField field = new BookerField(name, createCodewordValue(value));
/*  84 */     field.setWriter(new FieldWriter(field));
/*  85 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createObjectField(String name, BookerObject value) {
/*  89 */     BookerField field = new BookerField(name, createObjectValue(value));
/*  90 */     field.setWriter(new FieldWriter(field));
/*  91 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createVertexField(int index, double x, double y) {
/*  95 */     BookerField field = createListField("V" + (index + 1));
/*  96 */     field.add(createNumericValue(x));
/*  97 */     field.add(createNumericValue(x));
/*  98 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, ArrayList<FieldValue> values) {
/* 102 */     BookerField field = new BookerField(name, createListValue(values));
/* 103 */     field.setWriter(new FieldWriter(field));
/* 104 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name) {
/* 108 */     BookerField field = new BookerField(name, createListValue());
/* 109 */     field.setWriter(new FieldWriter(field));
/* 110 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, BookerObject firstElement) {
/* 114 */     BookerField field = createListField(name);
/* 115 */     field.add(createObjectValue(firstElement));
/* 116 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, double firstElement) {
/* 120 */     BookerField field = createListField(name);
/* 121 */     field.add(createNumericValue(firstElement));
/* 122 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, double x1, double x2, double x3, double x4, double x5, double x6) {
/* 126 */     BookerField field = createListField(name);
/* 127 */     field.add(createNumericValue(x1));
/* 128 */     field.add(createNumericValue(x2));
/* 129 */     field.add(createNumericValue(x3));
/* 130 */     field.add(createNumericValue(x4));
/* 131 */     field.add(createNumericValue(x5));
/* 132 */     field.add(createNumericValue(x6));
/* 133 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, int n1) {
/* 137 */     BookerField field = createListField(name);
/* 138 */     field.add(createNumericValue(n1));
/* 139 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, int n1, int n2) {
/* 143 */     BookerField field = createListField(name);
/* 144 */     field.add(createNumericValue(n1));
/* 145 */     field.add(createNumericValue(n2));
/* 146 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, int n1, int n2, int n3) {
/* 150 */     BookerField field = createListField(name);
/* 151 */     field.add(createNumericValue(n1));
/* 152 */     field.add(createNumericValue(n2));
/* 153 */     field.add(createNumericValue(n3));
/* 154 */     return field;
/*     */   }
/*     */   
/*     */   public static BookerField createListField(String name, String firstElement) {
/* 158 */     BookerField field = createListField(name);
/* 159 */     field.add(createCodewordValue(firstElement));
/* 160 */     return field;
/*     */   }
/*     */   
/*     */   public static RealValue createNumericValue(double value) {
/* 164 */     RealValue realValue = new RealValue(value);
/* 165 */     realValue.setWriter(new NumericWriter(realValue));
/* 166 */     return realValue;
/*     */   }
/*     */   
/*     */   public static AlphaValue createStringValue(String value) {
/* 170 */     AlphaValue stringValue = new AlphaValue(value);
/* 171 */     stringValue.setWriter(new StringValueWriter(stringValue));
/* 172 */     return stringValue;
/*     */   }
/*     */   
/*     */   public static AlphaValue createExpressionValue(String value) {
/* 176 */     AlphaValue expressionValue = new AlphaValue(value);
/* 177 */     expressionValue.setWriter(new KeywordExpressionWriter(expressionValue));
/* 178 */     return expressionValue;
/*     */   }
/*     */   
/*     */   public static AlphaValue createCodewordValue(String value) {
/* 182 */     AlphaValue codewordValue = new AlphaValue(value);
/* 183 */     codewordValue.setWriter(new CodewordWriter(codewordValue));
/* 184 */     return codewordValue;
/*     */   }
/*     */   
/*     */   public static ObjectValue createObjectValue(BookerObject object) {
/* 188 */     ObjectValue objectValue = new ObjectValue(object);
/* 189 */     objectValue.setWriter(new ObjectValueWriter(objectValue));
/* 190 */     return objectValue;
/*     */   }
/*     */   
/*     */   public static ListValue createListValue(ArrayList<FieldValue> values) {
/* 194 */     ListValue listValue = new ListValue(values);
/* 195 */     listValue.setWriter(new ListValueWriter(listValue));
/* 196 */     return listValue;
/*     */   }
/*     */   
/*     */   public static ListValue createListValue() {
/* 200 */     ListValue listValue = new ListValue();
/* 201 */     listValue.setWriter(new ListValueWriter(listValue));
/* 202 */     return listValue;
/*     */   }
/*     */ }


/* Location:              C:\JCI_AP\002_Tools\EPC Shade\EPC Shade_001.jar!\dunn\doe2\DOE2Factory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */