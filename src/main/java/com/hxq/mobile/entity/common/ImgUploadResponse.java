package com.hxq.mobile.entity.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 上传图片返回结果
 *
 */
public class ImgUploadResponse {

    private List<FileMeta> files = Lists.newArrayList();

    public void add(String id, String name, long size, String url, String thumbnail) {
        files.add(new FileMeta(id, name, size, url, thumbnail));
    }

    public void add(String name, String error) {
        files.add(new FileMeta(name, error));
    }

    public List<FileMeta> getFiles() {
        return files;
    }

    public void setFiles(List<FileMeta> files) {
        this.files = files;
    }

    /**
     * 文件信息
     */
    public static class FileMeta {
        /**
         * 文件记录id
         */
        private String id;
        /**
         * 名称
         */
        private String name;
        /**
         * 大小
         */
        private long size;
        /**
         * 上传文件后的地址
         */
        private String url;
        /**
         * 缩略图
         */
        private String thumbnail;
        /**
         * 错误信息
         */
        private String error;

        public FileMeta(String id, String name, long size, String url, String thumbnail) {
        	this.id = id;
            this.name = name;
            this.size = size;
            this.url = url;
            this.thumbnail = thumbnail;
        }

        public FileMeta(String name, String error) {
            this.name = name;
            this.error = error;
        }

        public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public long getSize() {
            return size;
        }
        public void setSize(long size) {
            this.size = size;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getThumbnail() {
            return thumbnail;
        }
        public String getError() {
            return error;
        }
        public void setError(String error) {
            this.error = error;
        }
    }
}