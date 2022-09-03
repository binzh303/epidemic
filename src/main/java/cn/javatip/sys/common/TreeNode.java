package cn.javatip.sys.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 李寻欢
 * @Date: 2021/11/22 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private int id;
    @JsonProperty("parentId")
    private int pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 0为不选中  1为选中
     */
    private String checkArr="0";

    /**
     * 首页左边导航菜单的构造器
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     */
    public TreeNode(int id, int pid, String title, String icon, String href, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * 部门 dtree的构造器
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(int id, int pid, String title, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
    }

    /**
     * 给角色分配权限的构造器
     * @param id
     * @param pid
     * @param title
     * @param spread
     * @param checkArr
     */
    public TreeNode(int id, int pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }
}
