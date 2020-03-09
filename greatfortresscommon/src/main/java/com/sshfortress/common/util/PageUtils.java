/**
 * winchance Inc.
 */
package com.sshfortress.common.util;

import com.sshfortress.common.contants.PageContants;


/**
 * 分页计算工具类
 */
public class PageUtils {

    /**
     * <p class="detail">
     * 功能：app端 根据页码 与每页行数 计算 分页需要的sql参数
     * </p>
     * 
     * @param currentPage
     *            第几页
     * @param pageSize
     *            每页展示行数
     */
    public static void computeAppPage(AppPager appPager) {

        // spring_mvc pager入参 总是不为null
        if (null == appPager) {
            appPager = new AppPager();
        }

        // 页码
        Integer pageSize = appPager.getPageSize();

        // 页码
        if (null == pageSize || pageSize.intValue() < PageContants.MIN_PAGE_SIZE
            || pageSize.intValue() > PageContants.MAX_PAGE_SIZE) {
            pageSize = PageContants.DEFULT_PAGE_SIZE;
            appPager.setPageSize(pageSize);
        }

        // 第几页
        Integer currentPage = appPager.getCurrentPage();

        if (null == currentPage || currentPage.intValue() < 1) {
            currentPage = 1;
            appPager.setCurrentPage(currentPage);
        }

        appPager.setStartRow(pageSize * (currentPage - 1));

    }

    /**
     * @param pager
     *            <ul>
     *            实时计算分页
     *            <li>当前第几页currentPage(页面传来的跳转页码:跳到第几页)</li>
     *            <li>每页展示数pageSize</li>
     *            <li>页面传来的请求（上一页、下一页...）askPage</li>
     *            </ul>
     * @param counts
     *            总记录数
     * 
     * @return 重新计算后的 Pager
     */
    public static void computePage(Pager pager, int counts) {
        // spring_mvc pager入参 总是不为null
        if (null == pager) {
            pager = new Pager();
        }
        // 必须的已知数
        // 总记录数
        pager.setTotalRecord(counts);
        int totalRecord = pager.getTotalRecord();

        // 每页展示数
        Integer pageSize = pager.getPageSize();
        // 首次展示
        if (null == pageSize || pageSize.intValue() < PageContants.MIN_PAGE_SIZE
            || pageSize.intValue() > PageContants.MAX_PAGE_SIZE) {
            pageSize = PageContants.DEFULT_PAGE_SIZE;
            pager.setPageSize(pageSize);
        }
        // 计算总页数
        int totalPage = totalRecord / pageSize;
        if (totalRecord % pageSize != 0 || totalRecord == 0) {
            // 有剩余行数的数据，新增一页
            totalPage += 1;
        }
        // 设置总页数
        pager.setTotalPage(totalPage);

        // 展示页(默认自选页)
        Integer currentPage = pager.getCurrentPage();

        if (null == currentPage || currentPage < 1) {
            currentPage = 1;
        }

        // 很重要，其他用户增删数据时，总页数计算变动，选择的页码不得超过总页码
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }

        // 计算调整后应该显示哪一页
        pager.setCurrentPage(currentPage);

        // 计算mysql limit 第一个参数 每页起始行
        int startRow = pageSize * (currentPage - 1);
        // 设置起始行数
        pager.setStartRow(startRow);
    }
}
