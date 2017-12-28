package kr.or.nextit.common.util;

public class PagingUtil {

    private int currentPage; // 현재페이지
    private int totalCount; // 전체 게시물 수
    private int totalPage; // 전체 페이지 수
    private int pageSize; // 한 페이지당 게시물 수
    private int pageCount; // 한 화면에 보여줄 페이지 번호 갯수
    private int startRow; // 시작 게시물 번호
    private int endRow; // 종료 게시물 번호
    private int startPage; // 시작 페이지 번호
    private int endPage; // 종료 페이지 번호

    // 페이지 링크 HTML 데이터를 담는 버퍼
    private StringBuffer pageHtml = new StringBuffer();
    
    public PagingUtil(int currentPage, int totalCount) {
        this(currentPage, totalCount, 20, 5);
    }

    public PagingUtil(int currentPage, int totalCount, int pageSize, int pageCount) {

        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageCount = pageCount;

        // 1. 총페이지 수 구하기
        //totalPage = (int) Math.ceil(totalCount / (double) pageSize);
        totalPage = (totalCount - 1) / pageSize + 1;

        
        // 2. 시작/종료 게시물 번호
        startRow = (currentPage - 1) * pageSize + 1;

        endRow = currentPage * pageSize;

        if (endRow > totalCount) {
            endRow = totalCount;
        }

        // 3. 시작/종료 페이지 번호
        startPage = ((currentPage - 1) / pageCount * pageCount) + 1;
        endPage = startPage + pageCount - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

 
        // 이전 생성
        if (currentPage > pageCount) {
            pageHtml.append("<li><a href='#' onclick='fn_search(" + (startPage - 1) + ")'>이전</a></li>");
        }

        // 페이지 네비게이터 생성
        for (int i = startPage; i <= endPage; i++) {

            if (currentPage == i) {
                pageHtml.append("<li class='active'><a href='#'>" + i + "</a></li>");
            } else {
                pageHtml.append(
                        "<li><a href='#' onclick='fn_search(" + i + ")'>" + i + "</a></li>");
            }
        }

        // 다음 생성
        if (totalPage - startPage >= pageCount) {
            pageHtml.append("<li><a href='#' onclick='fn_search(" + (endPage + 1) + ")'>다음</a></li>");
        }

       
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public StringBuffer getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(StringBuffer pageHtml) {
        this.pageHtml = pageHtml;
    }

    
}
