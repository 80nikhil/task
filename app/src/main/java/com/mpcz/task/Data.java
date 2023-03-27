package com.mpcz.task;

public class Data {

    public String repo_name;
    public String repo_link;
    public String repo_description;

    public Data(String repo_name, String repo_link, String repo_description) {
        this.repo_name = repo_name;
        this.repo_link = repo_link;
        this.repo_description = repo_description;
    }

    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public String getRepo_link() {
        return repo_link;
    }

    public void setRepo_link(String repo_link) {
        this.repo_link = repo_link;
    }

    public String getRepo_description() {
        return repo_description;
    }

    public void setRepo_description(String repo_description) {
        this.repo_description = repo_description;
    }
}
