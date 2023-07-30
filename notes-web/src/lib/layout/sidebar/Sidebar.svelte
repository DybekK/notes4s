<script lang="ts">
    import {Sidebar, SidebarWrapper} from 'flowbite-svelte'
    import NoteGroup from '$lib/layout/sidebar/NoteGroup.svelte'
</script>

<Sidebar class="lg:w-1/6 md:w-1/4 sm:w-1/2">
    <SidebarWrapper class="h-full">
        <NoteGroup/>
    </SidebarWrapper>
</Sidebar>
