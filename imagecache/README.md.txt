ͼƬ�������
ʹ��fresco��ܣ���ImagePipeline���л��棬ʹ��fresco��DraweeView��ʾ
ͼƬ����ʹ�÷�����
1������
app��build.gradle��dependencies�м���implementation project(path: ':imagecache')
app��AndroidManifest.xml�м���<service android:name="com.ggx.imagecache.ImageCacheIntentService" />
2����ʼ��
Application��onCreate�м���
	ImageCacheUtil.init(context,path,name);
	������
	context�������ģ�����ʹ��this���� getApplicationContext()
	path��	�ļ���·����Ĭ��sd������Environment.getExternalStorageDirectory()
	name��	�����ļ������ƣ�Ĭ��path
3��Ԥ����ͼƬ
����Ҫ�ĵط����룺ImageCacheUtil.prefetchImage(param)
	������
	param������ʹ��url��Ҳ����ʹ��ArrayList<String>
4����ʾ
ʹ��fresco��DraweeView��ʾͼƬ
	SimpleDraweeView simpleDraweeView;
	simpleDraweeView.setImageURI(imgUrl);