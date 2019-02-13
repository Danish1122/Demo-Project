/**
 * 
 */
 /*Scroll to top when arrow up clicked BEGIN*/
 $(window).scroll(function() {
     var height = $(window).scrollTop();
     if (height > 100) {
         $('#back2Top').fadeIn();
     } else {
         $('#back2Top').fadeOut();
     }
 });
 $(document).ready(function() {
     $("#back2Top").click(function(event) {
         event.preventDefault();
         $("html, body").animate({ scrollTop: 0 }, "slow");
         return false;
     });

 });
  /*Scroll to top when arrow up clicked END*/

 
 
 /* Grid start*/
 
 $('.show-list').click(function(){
		$('.wrapper').addClass('list-mode');
	});

	$('.hide-list').click(function(){
		$('.wrapper').removeClass('list-mode');
	});