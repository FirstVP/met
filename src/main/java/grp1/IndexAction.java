/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grp1;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import grp1.dao.NewsDao;
import grp1.model.News;

/**
 * 
 */
@Conversion()
public class IndexAction extends ActionSupport {
    
    private Date now = new Date(System.currentTimeMillis());

    public List<News> getNews() {
        return news;
    }

    private List<News> news;
    @TypeConversion(converter = "grp1.DateConverter")
    public Date getDateNow() { return now; }
    
    public String execute() throws Exception {
        now = new Date(System.currentTimeMillis());
        NewsDao newsDao = new NewsDao();
        news = newsDao.getAllNews();
        return SUCCESS;
    }
}
